package org.edu.via.sep4.lorawan_scope.view.main_view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import org.edu.via.sep4.lorawan_scope.view.ViewHandler;
import org.edu.via.sep4.lorawan_scope.view.ViewModelFactory;
import org.edu.via.sep4.lorawan_scope.view.downlink_view.DownlinkViewController;
import org.edu.via.sep4.lorawan_scope.view.uplink_view.UplinkViewController;

public class MainViewController {
    @FXML
    private TextField urlField;
    @FXML
    private Button connectButton;
    @FXML
    private CheckBox rememberUrl;
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

        mainViewModel.getIsConnected().addListener((obs, oldValue, newValue) -> {
            handleConnected();
        });

        urlField.setText(mainViewModel.getStoredWebSocketURL());
    }

    private void handleConnected() {
        connectButton.setDisable(true);
        urlField.setDisable(true);
        rememberUrl.setDisable(true);
    }

    @FXML
    private void connectButtonPressed(ActionEvent actionEvent) {
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

