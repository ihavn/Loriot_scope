package org.edu.via.sep4.lorawan_scope.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.edu.via.sep4.lorawan_scope.view.main_view.MainViewController;

import java.io.IOException;

public class ViewHandler {
    private final Scene currentScene;
    private Stage primaryStage;

    private final ViewModelFactory viewModelFactory;

    private MainViewController mainViewController;

    public ViewHandler(ViewModelFactory viewModelFactory) {
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

        Image icon = new Image(getClass().getResourceAsStream("icons8-analyze-24.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }

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

