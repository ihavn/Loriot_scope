package org.edu.via.sep4.lorawan_scope.model.uplink_model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface UplinkModel {
    void addListener(String name, PropertyChangeListener listener);

    void addUplinkMessage(UplinkData uplinkMessage);

    UplinkData getUplinkMessage(int index);

    ArrayList<UplinkData> getUplinkMessages();

    String getStoredWebsocketURL();

    void storeWebscocketURL(String url);

    void connectToWebSocket(String url);

    void sendDownLinkMessage(String devEUI, int port, boolean ack, String payload, int priority);

    void websocketConnected();
}
