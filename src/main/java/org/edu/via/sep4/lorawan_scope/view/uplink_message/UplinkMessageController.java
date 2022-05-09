package org.edu.via.sep4.lorawan_scope.view.uplink_message;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UplinkMessageController {
    @FXML
    private TableView<UplinkMessageView> uplinkDataTable;
    @FXML
    private TableColumn<UplinkMessageView, String> devEUICol;
    @FXML
    private TableColumn<UplinkMessageView, Integer> fcntUpCol;
    @FXML
    private TableColumn<UplinkMessageView, String> payloadCol;
    @FXML
    private TableColumn<UplinkMessageView, String> localTimeCol;
    @FXML
    private TableColumn<UplinkMessageView, Integer> portCol;
    private UplinkMessageViewModel uplinkMessageViewModel;

    public void init(UplinkMessageViewModel uplinkMessageViewModel) {
        this.uplinkMessageViewModel = uplinkMessageViewModel;

        devEUICol.setCellValueFactory(cellData -> cellData.getValue().getDevEUIProperty());
        fcntUpCol.setCellValueFactory(cellData -> cellData.getValue().getFcntUpProperty().asObject());
        payloadCol.setCellValueFactory(cellData -> cellData.getValue().getPayloadProperty());
        localTimeCol.setCellValueFactory(cellData -> cellData.getValue().getLocalTimeProperty());
        portCol.setCellValueFactory(cellData -> cellData.getValue().getPortProperty().asObject());

        uplinkDataTable.setItems(uplinkMessageViewModel.getUplinkTable());
    }
}

