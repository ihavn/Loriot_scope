package org.edu.via.sep4.lorawan_scope.view.uplink_view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import org.edu.via.sep4.lorawan_scope.view.ViewHandler;
import org.edu.via.sep4.lorawan_scope.view.ViewModelFactory;

public class UplinkViewController {
    @FXML
    private TableView<UplinkView> uplinkDataTable;
    @FXML
    private TableColumn<UplinkView, String> devEUICol;
    @FXML
    private TableColumn<UplinkView, String> fcntUpCol;
    @FXML
    private TableColumn<UplinkView, String> payloadCol;
    @FXML
    private TableColumn<UplinkView, String> localTimeCol;
    @FXML
    private TableColumn<UplinkView, String> portCol;

    private Region root;
    private UplinkViewModel uplinkViewModel;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root) {
        uplinkViewModel = viewModelFactory.getUplinkViewModel();
        devEUICol.setCellValueFactory(cellData -> cellData.getValue().getDevEUIProperty());
        fcntUpCol.setCellValueFactory(cellData -> cellData.getValue().getFcntUpProperty());
        payloadCol.setCellValueFactory(cellData -> cellData.getValue().getPayloadProperty());
        localTimeCol.setCellValueFactory(cellData -> cellData.getValue().getLocalTimeProperty());
        portCol.setCellValueFactory(cellData -> cellData.getValue().getPortProperty());

        uplinkDataTable.setItems(uplinkViewModel.getUplinkTable());

        this.root = root;
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
    }


}