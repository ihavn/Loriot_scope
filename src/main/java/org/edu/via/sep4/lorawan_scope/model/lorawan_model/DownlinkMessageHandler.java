package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import com.google.gson.Gson;

public class DownlinkMessageHandler {

    public String createDownLinkMessage(String devEUI, int port, boolean ack, String payload, int priority) {
        Gson gson = new Gson();
        return gson.toJson(new DownlinkMessage(devEUI, port, ack, priority, payload));
    }
}
