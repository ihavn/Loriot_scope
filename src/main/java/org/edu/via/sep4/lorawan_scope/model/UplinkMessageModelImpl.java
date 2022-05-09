package org.edu.via.sep4.lorawan_scope.model;

public class UplinkMessageModelImpl implements UplinkMessageModel {
    private String devEUI;
    private String localTime;
    private int fcntUp;
    private int port;
    private String payload;

    public UplinkMessageModelImpl(String devEUI, String localTime, int fcntUp, int port, String payload) {
        this.devEUI = devEUI;
        this.localTime = localTime;
        this.fcntUp = fcntUp;
        this.port = port;
        this.payload = payload;
    }

    @Override
    public String getDevEUI() {
        return devEUI;
    }

    @Override
    public String getLocalTime() {
        return localTime;
    }

    @Override
    public int getFcntUp() {
        return fcntUp;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getPayload() {
        return payload;
    }
}