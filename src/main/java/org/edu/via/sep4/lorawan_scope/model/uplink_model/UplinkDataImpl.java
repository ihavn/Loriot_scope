package org.edu.via.sep4.lorawan_scope.model;

public record UplinkMessageDataModelImpl(String devEUI, String localTime, String fcntUp, String port,
                                         String payload) implements UplinkMessageDataModel {
}