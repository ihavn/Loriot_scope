package org.edu.via.sep4.lorawan_scope.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.edu.via.sep4.lorawan_scope.view.main_view.MainViewController;

import java.io.IOException;

public class ViewHandler {
    private Scene currentScene;
    private Stage primaryStage;

    private ViewModelFactory viewModelFactory;

    private MainViewController mainViewController;

    public ViewHandler( ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        openView("MainView");
    }

    public void openView(String viewToOpen) throws IOException {
        Region root = null;

        switch (viewToOpen) {
            case "MainView":
                root = loadMainView("/org/edu/via/sep4/lorawan_scope/view/main_view/MainView.fxml");
                break;
        }

        currentScene.setRoot(root);
        String title = "";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }
    /*
        if ("UplinkMessage".equals(viewToOpen)) {
            loader.setLocation(getClass().getResource("/org/edu/via/sep4/lorawan_scope/view/lorawan_message/MainView.fxml"));
            root = loader.load();
            LoRaWANMessageController view = loader.getController();
            view.init(viewModelFactory.getUplinkMessageViewModel());
            stage.setTitle("LoRaWAN IoT Message Scope");
        }



       // Image icon = new Image("org/edu/via/sep4/lorawan_scope/view/icons8-analyze-24.png");
       // stage.getIcons().add(icon);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     */

    public void closeView() {
        primaryStage.close();
    }

    private Region loadMainView(String fxmlFile) {
        Region root = null;
        if (mainViewController == null) {
            // load from FXML
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                mainViewController = loader.getController();
                mainViewController.init(this, viewModelFactory.getUplinkMessageViewModel(), root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // reset window
            mainViewController.reset();
        }
        return mainViewController.getRoot();
    }
}

