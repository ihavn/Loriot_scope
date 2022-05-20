package org.edu.via.sep4.lorawan_scope;

import javafx.application.Application;
import javafx.stage.Stage;
import org.edu.via.sep4.lorawan_scope.core.ModelFactory;
import org.edu.via.sep4.lorawan_scope.view.ViewHandler;
import org.edu.via.sep4.lorawan_scope.core.ViewModelFactory;

public class LoRaWANScopeApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory modelFactory = new ModelFactory();
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory );
        viewHandler.start(stage);
    }
}



