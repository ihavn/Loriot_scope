package org.edu.via.sep4.lorawan_scope.model;

public interface UplinkMessageDataModel {
    String devEUI();
    String localTime();
    String fcntUp();
    String port();
    String payload();
}