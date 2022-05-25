package org.edu.via.sep4.lorawan_scope.view.downlink_view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import org.edu.via.sep4.lorawan_scope.view.ViewHandler;
import org.edu.via.sep4.lorawan_scope.view.ViewModelFactory;

public class DownlinkViewController {
    @FXML
    private TextField downlinkEui;
    @FXML
    private TextField downlinkPort;
    @FXML
    private TextField downlinkPayload;
    @FXML
    private CheckBox downlinkAck;
    @FXML
    private TextField downlinkPrio;
    @FXML
    private Button downlinkQueueButton;

    private DownlinkViewModel downlinkViewModel;
    private Region root;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root) {
        this.downlinkViewModel = viewModelFactory.getDownlinkViewModel();
        this.root = root;

        downlinkViewModel.getIsConnected().addListener((obs, oldValue, newValue) -> {
            handleConnected();
        });
    }

    private void handleConnected() {

        downlinkEui.setDisable(false);
        downlinkPayload.setDisable(false);
        downlinkPort.setDisable(false);
        downlinkAck.setDisable(false);
        downlinkPrio.setDisable(false);
        downlinkQueueButton.setDisable(false);
    }

    @FXML
    private void downlinkQueueButtonPressed(ActionEvent actionEvent) {
        if (downlinkEui.getText().equals("") || downlinkPort.getText().equals("") || downlinkPayload.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Down-link fields must be filled out!");
            alert.showAndWait();
        }

        downlinkViewModel.sendDownlinkMessage(downlinkEui.getText(), Integer.parseInt(downlinkPort.getText()), downlinkAck.isSelected(), downlinkPayload.getText(), Integer.parseInt(downlinkPrio.getText()));
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
    }
}

