module org.edu.via.sep4.lorawan_scope.lorawan_scope {
    requires javafx.controls;
    requires javafx.fxml;

    requires validatorfx;
    requires java.desktop;
    requires java.net.http;
    requires org.json;

    opens org.edu.via.sep4.lorawan_scope to javafx.fxml;
    exports org.edu.via.sep4.lorawan_scope;

    exports org.edu.via.sep4.lorawan_scope.view.lorawan_message;
    opens org.edu.via.sep4.lorawan_scope.view.lorawan_message to javafx.fxml;
}