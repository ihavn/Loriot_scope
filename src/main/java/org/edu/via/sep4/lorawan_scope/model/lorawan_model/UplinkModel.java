package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import java.beans.PropertyChangeListener;

public interface UplinkModel {
    void addListener(String name, PropertyChangeListener listener);

  //  void addUplinkMessage(UplinkMessageData uplinkMessage);

    LoriotMessageData getUplinkMessage(int index);
    // void websocketConnected();
}
