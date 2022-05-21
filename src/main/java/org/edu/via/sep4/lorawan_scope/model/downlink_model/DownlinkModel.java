package org.edu.via.sep4.lorawan_scope.model;

public interface DownlinkMessageHandlerModel {
    public void sendDownLinkMessage(String devEUI, int port, boolean ack, String payload);
}
