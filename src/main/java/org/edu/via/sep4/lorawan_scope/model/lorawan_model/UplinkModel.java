package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

public interface UplinkModel extends PropertyChangeSubject {
    LoriotMessageData getUplinkMessage(int index);
}
