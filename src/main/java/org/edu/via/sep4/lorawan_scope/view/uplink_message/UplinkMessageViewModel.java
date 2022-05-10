package org.edu.via.sep4.lorawan_scope.view.uplink_message;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.edu.via.sep4.lorawan_scope.model.UplinkMessageModel;
import org.edu.via.sep4.lorawan_scope.model.UplinkMessageModelHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class UplinkMessageViewModel {
    private UplinkMessageModelHandler model = null;

    private ObservableList<UplinkMessageView> uplinkTable;

    public ObservableList<UplinkMessageView> getUplinkTable() {
        return uplinkTable;
    }

    private void populateUplinkTable() {
        ArrayList<UplinkMessageModel> elements = model.getUplinkMessages();
        for (UplinkMessageModel e: elements) {
            uplinkTable.add(0, new UplinkMessageView(e.getDevEUI(), e.getLocalTime(),e.getFcntUp(),e.getPort(),e.getPayload()));
        }
    }

    private void populateTableRow(int index, UplinkMessageModel u) {
        uplinkTable.add(index, new UplinkMessageView(u.getDevEUI(), u.getLocalTime(),u.getFcntUp(),u.getPort(),u.getPayload()));
    }

    public UplinkMessageViewModel(UplinkMessageModelHandler model) {
        this.model = model;
        uplinkTable = FXCollections.observableArrayList();

        model.addListener("Add", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateTableView(evt);
            }

            private void updateTableView(PropertyChangeEvent evt) {
                Platform.runLater(() -> {
                    populateTableRow(0, model.getUplinkMessage((int)evt.getNewValue()));
                });
            }
        });

        populateUplinkTable();
    }
}
