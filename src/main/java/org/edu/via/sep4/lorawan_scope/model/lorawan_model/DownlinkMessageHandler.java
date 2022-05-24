package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import org.json.JSONObject;

public class DownlinkMessageHandler {

    public String createDownLinkMessage(String devEUI, int port, boolean ack, String payload, int priority) {
        JSONObject downlinkMessage = new JSONObject();
        downlinkMessage.put("cmd", "tx");
        downlinkMessage.put("EUI", devEUI);
        downlinkMessage.put("port", port);
        if (ack) {
            downlinkMessage.put("confirmed", ack);
        }
        downlinkMessage.put("prio", priority);
        downlinkMessage.put("data", payload);

        return downlinkMessage.toString();
    }
}
