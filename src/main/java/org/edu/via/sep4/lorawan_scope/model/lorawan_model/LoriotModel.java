package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import java.beans.PropertyChangeListener;

public interface LoriotModel {
    String getStoredWebSocketURL();
    void storeWebSocketURL(String url);
    void connectToWebSocket(String url);
    void addListener(String name, PropertyChangeListener listener);
}
