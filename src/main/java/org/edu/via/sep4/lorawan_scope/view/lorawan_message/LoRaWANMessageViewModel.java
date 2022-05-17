package org.edu.via.sep4.lorawan_scope.view.lorawan_message;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.edu.via.sep4.lorawan_scope.model.LoRaWANMessageHandlerModel;
import org.edu.via.sep4.lorawan_scope.model.UplinkMessageDataModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoRaWANMessageViewModel {
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private LoRaWANMessageHandlerModel model;

    private ObservableList<LoRaWANMessageView> uplinkTable;
    private StringProperty websocketURL;

    private boolean websocketConnected;

    public ObservableList<LoRaWANMessageView> getUplinkTable() {
        return uplinkTable;
    }

    private void populateTableRow(UplinkMessageDataModel u) {
        uplinkTable.add(0, new LoRaWANMessageView(u.devEUI(), u.localTime(), u.fcntUp(), u.port(), u.payload()));
    }

    public LoRaWANMessageViewModel(LoRaWANMessageHandlerModel model) {
        this.model = model;
        uplinkTable = FXCollections.observableArrayList();
        websocketURL = new SimpleStringProperty();

        model.addListener("Add", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateTableView(evt);
            }

            private void updateTableView(PropertyChangeEvent evt) {
                Platform.runLater(() -> populateTableRow(model.getUplinkMessage((int) evt.getNewValue())));
            }
        });

        model.addListener("WebsocketConnected", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Platform.runLater(() -> tellController());
            }

            private void tellController() {
                websocketConnected = true;
                changeSupport.firePropertyChange("WebsocketConnected", "", "Connect");
            }
        });
    }

    public void addListener(String name, PropertyChangeListener listener) {
        if(name == null) {
            changeSupport.addPropertyChangeListener(listener);
        }
        else {
            changeSupport.addPropertyChangeListener(name, listener);
        }
    }

    public void connectToWebSocket(String url) {
        model.connectToWebSocket(url);
    }

    public String getWebsocketURL() {
        return model.getStoredWebsocketURL();
    }

    public void storeWebscocketURL(String url) {
        model.storeWebscocketURL(url);
    }

    public void sendDownlinkMessage(String devEUI, int port, boolean ack, String payLoad, int priority) {
        model.sendDownLinkMessage(devEUI, port, ack, payLoad, priority);
    }
}
