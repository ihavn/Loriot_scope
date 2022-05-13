package org.edu.via.sep4.lorawan_scope;

import javafx.application.Application;
import javafx.stage.Stage;
import org.edu.via.sep4.lorawan_scope.core.ModelFactory;
import org.edu.via.sep4.lorawan_scope.core.ViewHandler;
import org.edu.via.sep4.lorawan_scope.core.ViewModelFactory;
import org.edu.via.sep4.lorawan_scope.lorawan.LoRaWANHandler;

public class LoRaWANScopeApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory modelFactory = new ModelFactory();
        new LoRaWANHandler(modelFactory.getUplinkMessageModel());
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ViewHandler viewHandler = new ViewHandler(stage, viewModelFactory );
        viewHandler.start();
    }
}



