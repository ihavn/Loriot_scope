package org.edu.via.sep4.lorawan_scope.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.edu.via.sep4.lorawan_scope.view.lorawan_message.LoRaWANMessageController;

import java.io.IOException;

public record ViewHandler(Stage stage, ViewModelFactory viewModelFactory) {

    public void start() throws Exception {
        openView("UplinkMessage");
    }

    public void openView(String viewToOpen) throws IOException {
        Scene scene;
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;

        if ("UplinkMessage".equals(viewToOpen)) {
            loader.setLocation(getClass().getResource("/org/edu/via/sep4/lorawan_scope/view/lorawan_message/UplinkMessage.fxml"));
            root = loader.load();
            LoRaWANMessageController view = loader.getController();
            view.init(viewModelFactory.getUplinkMessageViewModel());
            stage.setTitle("LoRaWAN IoT Message Scope");
        }

       //Image icon = new Image(getClass().getResourceAsStream("application.png"));
       // stage.getIcons().add(icon);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
