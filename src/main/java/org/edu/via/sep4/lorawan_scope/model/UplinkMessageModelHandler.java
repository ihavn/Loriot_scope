package org.edu.via.sep4.lorawan_scope.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface UplinkMessageModelHandler {
    void addListener(String name, PropertyChangeListener listener);
    void addUplinkMessage(UplinkMessageModel uplinkMessage);
    UplinkMessageModel getUplinkMessage(int index);
    ArrayList<UplinkMessageModel> getUplinkMessages();
}
