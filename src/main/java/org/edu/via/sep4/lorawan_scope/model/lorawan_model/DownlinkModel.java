package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import java.beans.PropertyChangeListener;

public interface DownlinkModel {
    void sendDownLinkMessage(String devEUI, int port, boolean ack, String payload, int priority);
    void addListener(String name, PropertyChangeListener listener);
}
