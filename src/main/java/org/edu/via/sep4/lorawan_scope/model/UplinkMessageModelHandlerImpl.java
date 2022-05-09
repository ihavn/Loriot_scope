package org.edu.via.sep4.lorawan_scope.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class UplinkMessageModelHandlerImpl implements UplinkMessageModelHandler {
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private ArrayList<UplinkMessageModel> uplinkMessages = new ArrayList<>();

    public UplinkMessageModelHandlerImpl() {
        for (int i = 0; i < 5; i++) {
            addUplinkMessage(new UplinkMessageModelImpl("ABCD1234", "2020-05-09 17:34:00", i, i+1, "1234ABF1"){
            });
        }
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
        changeSupport.firePropertyChange( "adddedUplinkMessage", null,0);
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
