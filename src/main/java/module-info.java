module org.edu.via.sep4.lorawan_scope.lorawan_scope {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;
    requires java.net.http;
    requires com.google.gson;

    opens org.edu.via.sep4.lorawan_scope to javafx.fxml;
    exports org.edu.via.sep4.lorawan_scope;

    exports org.edu.via.sep4.lorawan_scope.view.main_view;
    opens org.edu.via.sep4.lorawan_scope.view.main_view to javafx.fxml;

    exports org.edu.via.sep4.lorawan_scope.view.downlink_view;
    opens org.edu.via.sep4.lorawan_scope.view.downlink_view to javafx.fxml;
    exports org.edu.via.sep4.lorawan_scope.view.uplink_view;
    opens org.edu.via.sep4.lorawan_scope.view.uplink_view to javafx.fxml;

    exports org.edu.via.sep4.lorawan_scope.model.lorawan_model;
    opens org.edu.via.sep4.lorawan_scope.model.lorawan_model to com.google.gson;
}