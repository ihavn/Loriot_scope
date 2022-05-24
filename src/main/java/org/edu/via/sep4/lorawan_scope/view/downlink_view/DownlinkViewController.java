package org.edu.via.sep4.lorawan_scope.view.downlink_view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import org.edu.via.sep4.lorawan_scope.view.ViewHandler;
import org.edu.via.sep4.lorawan_scope.view.ViewModelFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DownlinkViewController {
    @FXML
    public TextField downlinkEui;
    public TextField downlinkPort;
    public TextField downlinkPayload;
    public CheckBox downlinkAck;
    public TextField downlinkPrio;
    public Button downlinkQueueButton;

    private DownlinkViewModel downlinkViewModel;
    private Region root;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root) {
        this.downlinkViewModel  = viewModelFactory.getDownlinkViewModel();
        this.root = root;

        listenToWebSocketConnect();
    }

    private void listenToWebSocketConnect() {
        downlinkViewModel.addListener("WebsocketConnected", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Platform.runLater(() -> handleConnected());
            }

            private void handleConnected() {

                downlinkEui.setDisable(false);
                downlinkPayload.setDisable(false);
                downlinkPort.setDisable(false);
                downlinkAck.setDisable(false);
                downlinkPrio.setDisable(false);
                downlinkQueueButton.setDisable(false);
            }
        });
    }

    public void downlinkQueueButtonPressed(ActionEvent actionEvent) {
        if (downlinkEui.getText().equals("") || downlinkPort.getText().equals("") || downlinkPayload.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Down-link fields must be filled out!");
            alert.showAndWait();
        }

        downlinkViewModel.sendDownlinkMessage(downlinkEui.getText(),Integer.parseInt(downlinkPort.getText()), downlinkAck.isSelected(), downlinkPayload.getText(), Integer.parseInt(downlinkPrio.getText()));
    }

    public Region getRoot() {
        return root;
    }
}

