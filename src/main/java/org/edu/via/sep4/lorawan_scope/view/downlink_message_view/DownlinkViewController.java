package org.edu.via.sep4.lorawan_scope.view.downlink_message_view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import org.edu.via.sep4.lorawan_scope.view.ViewHandler;
import org.edu.via.sep4.lorawan_scope.view.main_view.UplinkMessageViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DownlinkViewController {
    @FXML
    public TextField downlink_eui;
    public TextField downlink_port;
    public TextField downlink_payload;
    public CheckBox downlink_ack;
    public TextField downlink_prio;
    public Button downlink_queue_button;

    private UplinkMessageViewModel uplinkMessageViewModel;
    private Region root;

    public void init(ViewHandler viewHandler, UplinkMessageViewModel uplinkMessageViewModel, Region root) {
        this.uplinkMessageViewModel = uplinkMessageViewModel;
        this.root = root;

        uplinkMessageViewModel.addListener("WebsocketConnected", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Platform.runLater(() -> handleConnected());
            }

            private void handleConnected() {

                downlink_eui.setDisable(false);
                downlink_payload.setDisable(false);
                downlink_port.setDisable(false);
                downlink_ack.setDisable(false);
                downlink_prio.setDisable(false);
                downlink_queue_button.setDisable(false);
            }
        });
    }

    public void downlink_queue_button(ActionEvent actionEvent) {
        if (downlink_eui.getText().equals("") || downlink_port.getText().equals("") || downlink_payload.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Down-link fields must be filled out!");
            alert.showAndWait();
        }

        uplinkMessageViewModel.sendDownlinkMessage(downlink_eui.getText(),Integer.parseInt(downlink_port.getText()),downlink_ack.isSelected(), downlink_payload.getText(), Integer.parseInt(downlink_prio.getText()));
    }

    public Region getRoot() {
        return root;
    }
}

