package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

public final class LoriotMessageDataImpl implements LoriotMessageData {
    private final String devEUI;
    private final String localTime;
    private final String fcnt;
    private final String port;
    private final String payload;

    public LoriotMessageDataImpl(String devEUI, String localTime, String fcnt, String port,
                                 String payload) {
        this.devEUI = devEUI;
        this.localTime = localTime;
        this.fcnt = fcnt;
        this.port = port;
        this.payload = payload;
    }

    public String devEUI() {
        return devEUI;
    }

    public String localTime() {
        return localTime;
    }

    public String fcnt() {
        return fcnt;
    }

    public String port() {
        return port;
    }

    public String payload() {
        return payload;
    }

}