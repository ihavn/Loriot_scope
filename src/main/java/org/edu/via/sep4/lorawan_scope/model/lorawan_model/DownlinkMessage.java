package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

public class DownlinkMessage {
    private final String cmd = "tx";
    private final String EUI;
    private final int port;
    private final boolean ack;
    private final int prio;
    private final String data;

    public DownlinkMessage(String EUI, int port, boolean ack, int prio, String data) {
        this.EUI = EUI;
        this.port = port;
        this.ack = ack;
        this.prio = prio;
        this.data = data;
    }
}
