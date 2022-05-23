package org.edu.via.sep4.lorawan_scope.view.main_view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import org.edu.via.sep4.lorawan_scope.view.ViewHandler;
import org.edu.via.sep4.lorawan_scope.view.ViewModelFactory;
import org.edu.via.sep4.lorawan_scope.view.downlink_view.DownlinkViewController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainViewController {
    @FXML
    public TextField url_field;
    @FXML
    public Button connect_button;
    public CheckBox remember_url;

    private MainViewModel mainViewModel;
    @FXML
    private DownlinkViewController down_link_viewController;
    private ViewHandler viewHandler;
    private Region root;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root) {
        this.mainViewModel = viewModelFactory.getMainViewModel();
        this.viewHandler = viewHandler;
        this.root = root;

        down_link_viewController.init(viewHandler, viewModelFactory, root);


        url_field.setText(mainViewModel.getStoredWebSocketURL());

        listenToWebsocketConnect();
    }

    private void listenToWebsocketConnect() {
        mainViewModel.addListener("WebsocketConnected", new PropertyChangeListener() {
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

    public void connect_button_pressed(ActionEvent actionEvent) {
        if (remember_url.isSelected()) {
            mainViewModel.storeWebScocketURL(url_field.getCharacters().toString());
        }

        mainViewModel.connectToWebSocket(url_field.getCharacters().toString());
    }

    public void reset() {}

    public Region getRoot() {
        return root;
    }

}

