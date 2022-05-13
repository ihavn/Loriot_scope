package org.edu.via.sep4.lorawan_scope.model;

public interface UplinkMessageModel {
    String devEUI();
    String localTime();
    int fcntUp();
    int port();
    String payload();
}
