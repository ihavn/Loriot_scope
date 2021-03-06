package org.edu.via.sep4.lorawan_scope.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.edu.via.sep4.lorawan_scope.view.main_view.MainViewController;

public class ViewHandler {
    private final Scene currentScene;
    private Stage primaryStage;

    private final ViewModelFactory viewModelFactory;

    private MainViewController mainViewController;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openView("MainView");
    }

    public void openView(String viewToOpen) {
        Region root = null;

        switch (viewToOpen) {
            case "MainView":
                root = loadMainView("main_view/MainView.fxml");
                break;
        }

        currentScene.setRoot(root);
        String title = "IoT Message Scope";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }

        Image icon = new Image(getClass().getResourceAsStream("antenna.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }

    public void closeView() {
        primaryStage.close();
    }

    private Region loadMainView(String fxmlFile) {
        Region root;
        if (mainViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlFile));
                root = loader.load();
                mainViewController = loader.getController();
                mainViewController.init(this, viewModelFactory, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mainViewController.reset();
        }
        return mainViewController.getRoot();
    }
}

