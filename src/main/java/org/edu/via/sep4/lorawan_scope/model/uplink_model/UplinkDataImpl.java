package org.edu.via.sep4.lorawan_scope.model.uplink_model;

public record UplinkDataImpl(String devEUI, String localTime, String fcntUp, String port,
                             String payload) implements UplinkData {
}