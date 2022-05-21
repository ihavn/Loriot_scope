package org.edu.via.sep4.lorawan_scope.view.main_view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.edu.via.sep4.lorawan_scope.view.ViewHandler;
import org.edu.via.sep4.lorawan_scope.view.downlink_message_view.DownlinkViewController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewController {
    @FXML
    public TextField url_field;
    @FXML
    public Button connect_button;
    public CheckBox remember_url;
    @FXML
    private TableView<UplinkMessageView> uplinkDataTable;
    @FXML
    private TableColumn<UplinkMessageView, String> devEUICol;
    @FXML
    private TableColumn<UplinkMessageView, String> fcntUpCol;
    @FXML
    private TableColumn<UplinkMessageView, String> payloadCol;
    @FXML
    private TableColumn<UplinkMessageView, String> localTimeCol;
    @FXML
    private TableColumn<UplinkMessageView, String> portCol;
    @FXML
    private Pane downlinkPane;
    private UplinkViewModel uplinkViewModel;
    @FXML
    private DownlinkViewController down_link_viewController;
    private ViewHandler viewHandler;
    private Region root;

    public void init(ViewHandler viewHandler, UplinkViewModel uplinkViewModel, Region root) {
        this.uplinkViewModel = uplinkViewModel;
        this.viewHandler = viewHandler;
        this.root = root;

        down_link_viewController.init(viewHandler, uplinkViewModel, root);

        devEUICol.setCellValueFactory(cellData -> cellData.getValue().getDevEUIProperty());
        fcntUpCol.setCellValueFactory(cellData -> cellData.getValue().getFcntUpProperty());
        payloadCol.setCellValueFactory(cellData -> cellData.getValue().getPayloadProperty());
        localTimeCol.setCellValueFactory(cellData -> cellData.getValue().getLocalTimeProperty());
        portCol.setCellValueFactory(cellData -> cellData.getValue().getPortProperty());

        uplinkDataTable.setItems(uplinkViewModel.getUplinkTable());

        url_field.setText(uplinkViewModel.getWebsocketURL());

        listenToWebsocketConnect();
    }

    private void listenToWebsocketConnect() {
        uplinkViewModel.addListener("WebsocketConnected", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Platform.runLater(() -> handleConnected());
            }

            private void handleConnected() {
                connect_button.setDisable(true);
                url_field.setDisable(true);
            }
        });
    }

    public void connect(ActionEvent actionEvent) {
        if (remember_url.isSelected()) {
            uplinkViewModel.storeWebscocketURL(url_field.getCharacters().toString());
        }

        uplinkViewModel.connectToWebSocket(url_field.getCharacters().toString());
    }

    public void reset() {}

    public Region getRoot() {
        return root;
    }

}

