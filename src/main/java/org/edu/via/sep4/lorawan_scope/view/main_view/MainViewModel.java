package org.edu.via.sep4.lorawan_scope.view.main_view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.edu.via.sep4.lorawan_scope.model.ModelFactory;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.LoriotModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewModel implements PropertyChangeListener {
    private final LoriotModel loriotModel;
    private final BooleanProperty isConnected;

    public MainViewModel(ModelFactory modelFactory) {
        this.loriotModel = modelFactory.getLoriotModel();
        isConnected = new SimpleBooleanProperty();

        loriotModel.addListener("WebsocketConnected", this);
    }

    public String getStoredWebSocketURL() {
        return loriotModel.getStoredWebSocketURL();
    }

    public BooleanProperty getIsConnected() {
        return isConnected;
    }

    public void storeWebScocketURL(String url) {
        loriotModel.storeWebSocketURL(url);
    }

    public void connectToWebSocket(String url) {
        loriotModel.connectToWebSocket(url);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        isConnected.set(true);
    }
}
