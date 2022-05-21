package org.edu.via.sep4.lorawan_scope.view.main_view;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.edu.via.sep4.lorawan_scope.model.uplink_model.UplinkModel;
import org.edu.via.sep4.lorawan_scope.model.uplink_model.UplinkData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UplinkMessageViewModel {
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private final UplinkModel model;

    private final ObservableList<UplinkMessageView> uplinkTable;
    private final StringProperty websocketURL;

    private boolean websocketConnected;

    public ObservableList<UplinkMessageView> getUplinkTable() {
        return uplinkTable;
    }

    private void populateTableRow(UplinkData u) {
        uplinkTable.add(0, new UplinkMessageView(u.devEUI(), u.localTime(), u.fcntUp(), u.port(), u.payload()));
    }

    public UplinkMessageViewModel(UplinkModel model) {
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

    public void clear() {
    }
}
