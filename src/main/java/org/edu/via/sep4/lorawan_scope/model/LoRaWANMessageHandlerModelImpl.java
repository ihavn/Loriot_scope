package org.edu.via.sep4.lorawan_scope.model;

import org.edu.via.sep4.lorawan_scope.model.lorawan.LoRaWANHandler;
import org.edu.via.sep4.lorawan_scope.model.lorawan.LoRaWANHandlerImpl;
import org.json.JSONObject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoRaWANMessageHandlerModelImpl implements LoRaWANMessageHandlerModel {
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private final ArrayList<UplinkMessageDataModel> uplinkMessages = new ArrayList<>();
    private final LoRaWANHandler loRaWanHandler;

    public LoRaWANMessageHandlerModelImpl() {
        this.loRaWanHandler = new LoRaWANHandlerImpl(this);
    }

    @Override
    public void addListener(String name, PropertyChangeListener listener) {
        if(name == null) {
            changeSupport.addPropertyChangeListener(listener);
        }
        else {
            changeSupport.addPropertyChangeListener(name, listener);
        }
    }

    @Override
    public void addUplinkMessage(UplinkMessageDataModel uplinkMessage) {
            uplinkMessages.add(uplinkMessage);
            changeSupport.firePropertyChange("Add", "", uplinkMessages.size() - 1);
    }

    @Override
    public UplinkMessageDataModel getUplinkMessage(int index) {
        return uplinkMessages.get(index);
    }

    @Override
    public ArrayList<UplinkMessageDataModel> getUplinkMessages() {
        return uplinkMessages;
    }

    @Override
    public String getStoredWebsocketURL() {
        try {
            File urlFile = new File("WebsocketURL.txt");
            if (urlFile.exists()) {
                Scanner urlReader = new Scanner(urlFile);
                String url = urlReader.nextLine();
                urlReader.close();

                return url;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public void storeWebscocketURL(String url) {
        try {
            FileWriter myWriter = new FileWriter("WebsocketURL.txt");
            myWriter.write(url);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectToWebSocket(String url) {
        loRaWanHandler.connectToWebSocket(url);
    }

    @Override
    public void sendDownLinkMessage(String devEUI, int port, boolean ack, String payload, int priority) {
        JSONObject downlinkMessage = new JSONObject();
        downlinkMessage.put("cmd", "tx");
        downlinkMessage.put("EUI", devEUI);
        downlinkMessage.put("port", port);
        downlinkMessage.put("priority", priority);
        if (ack) {
            downlinkMessage.put("confirmed", ack);
        }
        downlinkMessage.put("data", payload);
        sendDownLinkMessage(downlinkMessage.toString());
    }

    @Override
    public void websocketConnected() {
        changeSupport.firePropertyChange("WebsocketConnected", "", "Connect");
    }

    public void sendDownLinkMessage(String json) {
        loRaWanHandler.sendDownLinkMessage(json);
    }
}