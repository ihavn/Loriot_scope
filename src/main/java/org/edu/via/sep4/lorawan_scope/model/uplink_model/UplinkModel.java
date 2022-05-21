package org.edu.via.sep4.lorawan_scope.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface LoRaWANMessageHandlerModel {
    void addListener(String name, PropertyChangeListener listener);

    void addUplinkMessage(UplinkMessageDataModel uplinkMessage);

    UplinkMessageDataModel getUplinkMessage(int index);

    ArrayList<UplinkMessageDataModel> getUplinkMessages();

    public String getStoredWebsocketURL();

    public void storeWebscocketURL(String url);

    public void connectToWebSocket(String url);

    public void sendDownLinkMessage(String devEUI, int port, boolean ack, String payload, int priority);

    void websocketConnected();
}
