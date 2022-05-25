package org.edu.via.sep4.lorawan_scope.view.downlink_view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.edu.via.sep4.lorawan_scope.model.ModelFactory;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.DownlinkModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DownlinkViewModel implements PropertyChangeListener {
    private final DownlinkModel downlinkModel;
    private final BooleanProperty isConnected;

    public DownlinkViewModel(ModelFactory modelFactory) {
        this.downlinkModel = modelFactory.getDownlinkModel();
        isConnected = new SimpleBooleanProperty();

        downlinkModel.addListener("WebsocketConnected", this);
    }

    public BooleanProperty getIsConnected() {
        return isConnected;
    }

    public void sendDownlinkMessage(String eui, int port, boolean ack, String payload, int prio) {
        downlinkModel.sendDownLinkMessage(eui, port, ack, payload, prio);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        isConnected.set(true);
    }
}
