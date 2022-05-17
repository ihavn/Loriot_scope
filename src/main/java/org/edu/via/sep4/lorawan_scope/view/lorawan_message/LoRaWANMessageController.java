package org.edu.via.sep4.lorawan_scope.view.lorawan_message;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoRaWANMessageController {
    @FXML
    public TextField url_field;
    @FXML
    public Button connect_button;
    public CheckBox remember_url;
    public TextField downlink_eui;
    public TextField downlink_port;
    public TextField downlink_payload;
    public CheckBox downlink_ack;
    public TextField downlink_prio;
    public Button downlink_queue_btton;
    @FXML
    private TableView<LoRaWANMessageView> uplinkDataTable;
    @FXML
    private TableColumn<LoRaWANMessageView, String> devEUICol;
    @FXML
    private TableColumn<LoRaWANMessageView, String> fcntUpCol;
    @FXML
    private TableColumn<LoRaWANMessageView, String> payloadCol;
    @FXML
    private TableColumn<LoRaWANMessageView, String> localTimeCol;
    @FXML
    private TableColumn<LoRaWANMessageView, String> portCol;

    private LoRaWANMessageViewModel loRaWANMessageViewModel;

    public void init(LoRaWANMessageViewModel loRaWANMessageViewModel) {
        this.loRaWANMessageViewModel = loRaWANMessageViewModel;

        devEUICol.setCellValueFactory(cellData -> cellData.getValue().getDevEUIProperty());
        fcntUpCol.setCellValueFactory(cellData -> cellData.getValue().getFcntUpProperty());
        payloadCol.setCellValueFactory(cellData -> cellData.getValue().getPayloadProperty());
        localTimeCol.setCellValueFactory(cellData -> cellData.getValue().getLocalTimeProperty());
        portCol.setCellValueFactory(cellData -> cellData.getValue().getPortProperty());

        uplinkDataTable.setItems(loRaWANMessageViewModel.getUplinkTable());

        url_field.setText(loRaWANMessageViewModel.getWebsocketURL());

        loRaWANMessageViewModel.addListener("WebsocketConnected", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Platform.runLater(() -> handleConnected());
            }

            private void handleConnected() {
                connect_button.setDisable(true);
                url_field.setDisable(true);
                downlink_eui.setDisable(false);
                downlink_payload.setDisable(false);
                downlink_port.setDisable(false);
                downlink_ack.setDisable(false);
                downlink_prio.setDisable(false);
                downlink_queue_btton.setDisable(false);
            }
        });
    }

    public void connect(ActionEvent actionEvent) {
        if (remember_url.isSelected()) {
            loRaWANMessageViewModel.storeWebscocketURL(url_field.getCharacters().toString());
        }

        loRaWANMessageViewModel.connectToWebSocket(url_field.getCharacters().toString());
    }

    public void downlink_queue_button(ActionEvent actionEvent) {
        if (downlink_eui.getText().equals("") || downlink_port.getText().equals("") || downlink_payload.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Down-link fields must be filled out!");
            alert.showAndWait();
        }

        loRaWANMessageViewModel.sendDownlinkMessage(downlink_eui.getText(),Integer.parseInt(downlink_port.getText()),downlink_ack.isSelected(), downlink_payload.getText(), Integer.parseInt(downlink_prio.getText()));
    }
}

