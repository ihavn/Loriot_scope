package org.edu.via.sep4.lorawan_scope.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class UplinkMessageModelHandlerImpl implements UplinkMessageModelHandler {
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private final ArrayList<UplinkMessageModel> uplinkMessages = new ArrayList<>();

    public UplinkMessageModelHandlerImpl() {
    }

    @Override
    public void addListener(String name, PropertyChangeListener listener) {
        if(name == null) {
            changeSupport.addPropertyChangeListener(listener);
        }
        else {
            changeSupport.addPropertyChangeListener(name, listener);
        }
    }

    @Override
    public void addUplinkMessage(UplinkMessageModel uplinkMessage) {
            uplinkMessages.add(uplinkMessage);
            changeSupport.firePropertyChange("Add", "", uplinkMessages.size() - 1);
    }

    @Override
    public UplinkMessageModel getUplinkMessage(int index) {
        return uplinkMessages.get(index);
    }

    @Override
    public ArrayList<UplinkMessageModel> getUplinkMessages() {
        return uplinkMessages;
    }
}
