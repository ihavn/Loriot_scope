package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject {
    void addListener(String name, PropertyChangeListener listener);
}
