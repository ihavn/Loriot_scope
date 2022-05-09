package org.edu.via.sep4.lorawan_scope.model;

public interface UplinkMessageModel {
    String getDevEUI();
    String getLocalTime();
    int getFcntUp();
    int getPort();
    String getPayload();
}
