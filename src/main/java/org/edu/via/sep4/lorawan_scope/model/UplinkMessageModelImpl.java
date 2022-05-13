package org.edu.via.sep4.lorawan_scope.model;

public record UplinkMessageModelImpl(String devEUI, String localTime, int fcntUp, int port,
                                     String payload) implements UplinkMessageModel {
}