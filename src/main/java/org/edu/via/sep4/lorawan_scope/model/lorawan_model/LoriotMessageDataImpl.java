package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

public record LoriotMessageDataImpl(String devEUI, String localTime, String fcnt, String port,
                                    String payload) implements LoriotMessageData {
}