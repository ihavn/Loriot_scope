package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

public interface LoRaWANHandler {
    void connectToWebSocket(String url);
    void sendDownLinkMessage(String json);
}
