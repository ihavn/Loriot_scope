package org.edu.via.sep4.lorawan_scope.view.main_view;

import org.edu.via.sep4.lorawan_scope.model.ModelFactory;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.LoriotModel;

import java.beans.PropertyChangeListener;

public class MainViewModel {
    private final LoriotModel loriotModel;

    public MainViewModel(ModelFactory modelFactory) {
        this.loriotModel = modelFactory.getLoriotModel();
    }

    public String getStoredWebSocketURL() {
        return loriotModel.getStoredWebSocketURL();
    }

    public void addListener(String name, PropertyChangeListener listener) {
        loriotModel.addListener(name,listener);
    }

    public void storeWebScocketURL(String url) {
        loriotModel.storeWebSocketURL(url);
    }

    public void connectToWebSocket(String url) {
        loriotModel.connectToWebSocket(url);
    }
}
