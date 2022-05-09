package org.edu.via.sep4.lorawan_scope.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.edu.via.sep4.lorawan_scope.view.uplink_message.UplinkMessageController;

import java.io.IOException;

public class ViewHandler {
    private Stage stage;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(Stage stage, ViewModelFactory viewModelFactory) {
        this.stage = stage;
        this.viewModelFactory = viewModelFactory;
    }

    public void start() throws Exception {
        openView("UplinkMessage");
    }

    public void openView(String viewToOpen) throws IOException {
        Scene scene = null;
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;

        if ("UplinkMessage".equals(viewToOpen)) {
            loader.setLocation(getClass().getResource("/org/edu/via/sep4/lorawan_scope/view/uplink_message/UplinkMessage.fxml"));
            root = loader.load();
            UplinkMessageController view = loader.getController();
            view.init(viewModelFactory.getUplinkMessageViewModel());
            stage.setTitle("LoRaWAN Uplink Messages");
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
