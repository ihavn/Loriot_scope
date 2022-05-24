package org.edu.via.sep4.lorawan_scope.view.main_view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import org.edu.via.sep4.lorawan_scope.view.ViewHandler;
import org.edu.via.sep4.lorawan_scope.view.ViewModelFactory;
import org.edu.via.sep4.lorawan_scope.view.downlink_view.DownlinkViewController;
import org.edu.via.sep4.lorawan_scope.view.uplink_view.UplinkViewController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewController {
    @FXML
    public TextField urlField;
    @FXML
    public Button connectButton;
    public CheckBox rememberUrl;
    private MainViewModel mainViewModel;
    @FXML
    private DownlinkViewController downlinkViewController;
    @FXML
    private UplinkViewController uplinkViewController;
    private Region root;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root) {
        this.mainViewModel = viewModelFactory.getMainViewModel();
        this.root = root;

        downlinkViewController.init(viewHandler, viewModelFactory, root);
        uplinkViewController.init(viewHandler, viewModelFactory, root);

        urlField.setText(mainViewModel.getStoredWebSocketURL());

        listenToWebSocketConnect();
    }

    private void listenToWebSocketConnect() {
        mainViewModel.addListener("WebsocketConnected", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Platform.runLater(() -> handleConnected());
            }

            private void handleConnected() {
                connectButton.setDisable(true);
                urlField.setDisable(true);
                rememberUrl.setDisable(true);
            }
        });
    }

    public void connectButtonPressed(ActionEvent actionEvent) {
        if (rememberUrl.isSelected()) {
            mainViewModel.storeWebScocketURL(urlField.getCharacters().toString());
        }

        mainViewModel.connectToWebSocket(urlField.getCharacters().toString());
    }

    public void reset() {
    }

    public Region getRoot() {
        return root;
    }

}

