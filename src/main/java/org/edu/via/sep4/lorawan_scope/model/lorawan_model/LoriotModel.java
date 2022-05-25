package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

public interface LoriotModel extends PropertyChangeSubject {
    String getStoredWebSocketURL();

    void storeWebSocketURL(String url);

    void connectToWebSocket(String url);
}
