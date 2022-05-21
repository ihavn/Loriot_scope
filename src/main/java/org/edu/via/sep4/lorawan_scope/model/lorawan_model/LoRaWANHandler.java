package org.edu.via.sep4.lorawan_scope.model.lorawan;

public interface LoRaWANHandler {
    public void connectToWebSocket(String url);
    public void sendDownLinkMessage(String json);
}
