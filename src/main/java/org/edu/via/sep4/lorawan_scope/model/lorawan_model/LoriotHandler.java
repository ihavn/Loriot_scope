package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import com.google.gson.Gson;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoriotHandler implements UplinkModel, DownlinkModel, LoriotModel {
    private WebSocketClient websocketClient;
    private final DownlinkMessageHandler downlinkMessageHandler;

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private final ArrayList<LoriotMessageData> loriotMessages = new ArrayList<>();

    public LoriotHandler() {
        downlinkMessageHandler = new DownlinkMessageHandler();
    }

    @Override
    public String getStoredWebSocketURL() {
        try {
            File urlFile = new File("WebsocketURL.txt");
            if (urlFile.exists()) {
                Scanner urlReader = new Scanner(urlFile);
                String url = urlReader.nextLine();
                urlReader.close();

                return url;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public void storeWebSocketURL(String url) {
        try {
            FileWriter myWriter = new FileWriter("WebsocketURL.txt");
            myWriter.write(url);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToWebSocket(String url) {
        websocketClient = new WebSocketClient(this);
        websocketClient.connectToWebSocket(url);
        new LoriotCache(this);
    }

    @Override
    public void sendDownLinkMessage(String devEUI, int port, boolean ack, String payload, int priority) {
        sendDownLinkMessage(downlinkMessageHandler.createDownLinkMessage(devEUI, port, ack, payload, priority));
    }

    @Override
    public void addListener(String name, PropertyChangeListener listener) {
        if (name == null) {
            changeSupport.addPropertyChangeListener(listener);
        } else {
            changeSupport.addPropertyChangeListener(name, listener);
        }
    }

    @Override
    public LoriotMessageData getUplinkMessage(int index) {
        return loriotMessages.get(index);
    }

    public void unpackLoRaWANMessage(String json) {
        Gson gson = new Gson();
        LoriotMessage loriotMessage = gson.fromJson(json, LoriotMessage.class);

        switch (loriotMessage.getType()) {
            case UPLINK:
                addLoriotMessage(gson.fromJson(json, LoriotMessage.class));
                break;

            case CACHE:
                for (int i = loriotMessage.getUplinkMessages().size() - 1; i >= 0; i--) {
                    addLoriotMessage(loriotMessage.getUplinkMessages().get(i));
                }
                break;

            case QUEUED:
                if (loriotMessage.getSuccess() != null) {
                    addLoriotMessage(loriotMessage.getEUI(), "", "", "", loriotMessage.getData() + " (Enqueued for sending)");
                } else {
                    addLoriotMessage(loriotMessage.getEUI(), "", "", "", loriotMessage.getError());
                }
                break;

            case SEND:
                addLoriotMessage(loriotMessage.getEUI(), loriotMessage.getLocalTime(), "", "", "(Enqueued data sent)");
                break;

            default:
        }
    }

    private void addLoriotMessage(String eui, String localTime, String fcnt, String port, String payload) {
        loriotMessages.add(new LoriotMessageDataImpl(eui, localTime, fcnt, port, payload));
        changeSupport.firePropertyChange("Add", "", loriotMessages.size() - 1);
    }

    private void addLoriotMessage(LoriotMessage uplinkMessage) {
        addLoriotMessage(uplinkMessage.getEUI(), uplinkMessage.getLocalTime(), uplinkMessage.getFcntString(), uplinkMessage.getPortString(), uplinkMessage.getData());
    }

    public void websocketConnected() {
        changeSupport.firePropertyChange("WebsocketConnected", "", "Connect");
    }

    public void sendDownLinkMessage(String json) {
        websocketClient.sendDownLinkMessage(json);
    }
}
