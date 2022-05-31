package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoriotMessage {
    private String EUI;
    private long ts;
    private int fcnt;
    private int port;
    private String data;
    private String success;
    private String error;
    private String cmd;
    private ArrayList<LoriotMessage> cache;

    public String getEUI() {
        return EUI;
    }

    public long getTs() {
        return ts;
    }

    public String getLocalTime() {
        Date date = new Date(ts); // convert seconds to milliseconds
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss.SSS"); // the format of your date
        return dateFormat.format(date);
    }

    public int getFcnt() {
        return fcnt;
    }

    public String getFcntString() {
        return Integer.toString(fcnt);
    }

    public int getPort() {
        return port;
    }

    public String getPortString() {
        return Integer.toString(port);
    }

    public String getData() {
        return data;
    }

    public ArrayList<LoriotMessage> getUplinkMessages() {
        return cache;
    }

    public String getSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public String getCmd() {
        return cmd;
    }

    public LoriotMessageType getType() {
        switch (cmd) {
            case "rx":
                return LoriotMessageType.UPLINK;
            case "cq":
                return LoriotMessageType.CACHE;
            case "txd":
                return LoriotMessageType.SEND;
            case "tx":
                return LoriotMessageType.QUEUED;
            default:
                return LoriotMessageType.UNKNOWN;
        }
    }
}
