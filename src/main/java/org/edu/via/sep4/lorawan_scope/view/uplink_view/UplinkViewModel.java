package org.edu.via.sep4.lorawan_scope.view.uplink_view;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.edu.via.sep4.lorawan_scope.model.ModelFactory;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.LoriotMessageData;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.UplinkModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UplinkViewModel {
    private final ObservableList<UplinkView> uplinkTable;
    private final UplinkModel uplinkModel;

    public UplinkViewModel(ModelFactory modelFactory) {
        this.uplinkModel = modelFactory.getUplinkModel();

        uplinkTable = FXCollections.observableArrayList();
        StringProperty webSocketURL = new SimpleStringProperty();

        listenToUplinkMessages();
    }

    public ObservableList<UplinkView> getUplinkTable() {
        return uplinkTable;
    }

    private void populateTableRow(LoriotMessageData u) {
        uplinkTable.add(0, new UplinkView(u.devEUI(), u.localTime(), u.fcnt(), u.port(), u.payload()));
    }

    private void listenToUplinkMessages() {
        uplinkModel.addListener("Add", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                handleUplinkMessage(evt);
            }

            private void handleUplinkMessage(PropertyChangeEvent evt) {
                Platform.runLater(() -> populateTableRow(uplinkModel.getUplinkMessage((int) evt.getNewValue())));
            }
        });
    }
}
