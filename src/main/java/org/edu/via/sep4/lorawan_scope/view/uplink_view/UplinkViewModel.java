package org.edu.via.sep4.lorawan_scope.view.uplink_view;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.edu.via.sep4.lorawan_scope.model.ModelFactory;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.UplinkModel;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.LoriotMessageData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UplinkViewModel {
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private final ObservableList<UplinkView> uplinkTable;
    private final StringProperty webSocketURL;

    private boolean webSocketConnected;

    private UplinkModel uplinkModel;
    public ObservableList<UplinkView> getUplinkTable() {
        return uplinkTable;
    }

    private void populateTableRow(LoriotMessageData u) {
        uplinkTable.add(0, new UplinkView(u.devEUI(), u.localTime(), u.fcnt(), u.port(), u.payload()));
    }

    public UplinkViewModel(ModelFactory modelFactory) {
        this.uplinkModel = modelFactory.getUplinkModel();

        uplinkTable = FXCollections.observableArrayList();
        webSocketURL = new SimpleStringProperty();

        listenToUplinkMessages();
        listenToLoRaWAN();
    }

    private void listenToLoRaWAN() {
        uplinkModel.addListener("WebsocketConnected", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Platform.runLater(() -> tellListeners());
            }

            private void tellListeners() {
                webSocketConnected = true;
                changeSupport.firePropertyChange("WebsocketConnected", "", "Connect");
            }
        });
    }

    private void listenToUplinkMessages() {
        uplinkModel.addListener("Add", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateTableView(evt);
            }

            private void updateTableView(PropertyChangeEvent evt) {
                Platform.runLater(() -> populateTableRow(uplinkModel.getUplinkMessage((int) evt.getNewValue())));
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
}
