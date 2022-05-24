package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class LoriotHandler implements UplinkModel, DownlinkModel, LoriotModel {
    private WebSocketClient websocketClient;
    private final DownlinkMessageHandler downlinkMessageHandler;

    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private final ArrayList<LoriotMessageData> loriotMessages = new ArrayList<>();
    public LoriotHandler() {
        UplinkMessageHandler uplinkMessageHandler = new UplinkMessageHandler();
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
        } catch (FileNotFoundException e) {
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
        if(name == null) {
            changeSupport.addPropertyChangeListener(listener);
        }
        else {
            changeSupport.addPropertyChangeListener(name, listener);
        }
    }

    @Override
    public LoriotMessageData getUplinkMessage(int index) {
        return loriotMessages.get(index);
    }

    void unpackLoRaWANMessage(String json) {
        JSONObject jo = new JSONObject(json);
        if (jo.getString("cmd").equals("rx")) {
            addLoriotMessage(jo.getString("EUI"), tsToString(jo.getLong("ts")), Integer.toString(jo.getInt("fcnt")), Integer.toString(jo.getInt("port")), jo.getString("data"));
        } else if (jo.getString("cmd").equals("cq")) {
            JSONArray jsonArray = jo.getJSONArray("cache");
            Object item;
            for (int i = jsonArray.length() - 1; i >= 0; i--) {
                item = jsonArray.get(i);
                if (item instanceof JSONObject) {
                    jo = (JSONObject) item;
                    try {
                        addLoriotMessage(jo.getString("EUI"), tsToString(jo.getLong("ts")), Integer.toString(jo.getInt("fcnt")), Integer.toString(jo.getInt("port")), jo.getString("data"));
                    } catch (JSONException e) {
                        System.out.println();
                    }
                }
            }
        } else if (jo.getString("cmd").equals("tx")) {
            if (jo.has("success")) {
                addLoriotMessage(jo.getString("EUI"), "", "", "", jo.getString("data") + " (Enqueued for sending)");
            } else {
                addLoriotMessage(jo.getString("EUI"), "", "", "", jo.getString("error"));
            }
        } else if (jo.getString("cmd").equals("txd")) {
            addLoriotMessage(jo.getString("EUI"), tsToString(jo.getLong("ts")), "", "", "(Enqueued data sent)");
        }

    }

    private void addLoriotMessage(String eui, String localTime, String fcnt, String port, String payload) {
        loriotMessages.add(new LoriotMessageDataImpl(eui, localTime, fcnt, port, payload));
        changeSupport.firePropertyChange("Add", "", loriotMessages.size() - 1);
    }

    private String tsToString(long ts) {
        Date date = new Date(ts); // convert seconds to milliseconds
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss.SSS"); // the format of your date
        return dateFormat.format(date);
    }

    void websocketConnected() {
            changeSupport.firePropertyChange("WebsocketConnected", "", "Connect");
    }

    void sendDownLinkMessage(String json) {
        websocketClient.sendDownLinkMessage(json);
    }
}
