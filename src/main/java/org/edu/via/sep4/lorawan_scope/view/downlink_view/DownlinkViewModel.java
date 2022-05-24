package org.edu.via.sep4.lorawan_scope.view.downlink_view;

import org.edu.via.sep4.lorawan_scope.model.ModelFactory;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.DownlinkModel;

import java.beans.PropertyChangeListener;

public class DownlinkViewModel {
    private final DownlinkModel downlinkModel;

    public DownlinkViewModel(ModelFactory modelFactory) {
        this.downlinkModel = modelFactory.getDownlinkModel();
    }

    public void addListener(String name, PropertyChangeListener listener) {
        downlinkModel.addListener(name, listener);
    }

    public void sendDownlinkMessage(String eui,int port,boolean ack, String payload, int prio) {
        downlinkModel.sendDownLinkMessage(eui, port, ack, payload, prio);
    }
}
