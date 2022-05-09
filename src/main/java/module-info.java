module org.edu.via.sep4.lorawan_scope.lorawan_scope {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.net.http;
    requires org.json;

    opens org.edu.via.sep4.lorawan_scope to javafx.fxml;
    exports org.edu.via.sep4.lorawan_scope;

    exports org.edu.via.sep4.lorawan_scope.view.uplink_message;
    opens org.edu.via.sep4.lorawan_scope.view.uplink_message to javafx.fxml;
}