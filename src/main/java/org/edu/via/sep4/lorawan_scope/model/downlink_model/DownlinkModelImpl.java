package org.edu.via.sep4.lorawan_scope.model;

import org.edu.via.sep4.lorawan_scope.model.lorawan.LoRaWANHandler;
import org.json.JSONObject;

public class DownlinkMessageHandlerModelImpl implements DownlinkMessageHandlerModel {
    private final LoRaWANHandler loRaWANHandler;

    public DownlinkMessageHandlerModelImpl(LoRaWANHandler loRaWANHandler) {
        this.loRaWANHandler = loRaWANHandler;
    }

    @Override
    public void sendDownLinkMessage(String devEUI, int port, boolean ack, String payload) {
        JSONObject downlinkMessage = new JSONObject();
        downlinkMessage.put("cmd", "tx");
        downlinkMessage.put("EUI", devEUI);
        downlinkMessage.put("port", port);
        if (ack) {
            downlinkMessage.put("confirmed", ack);
        }
        downlinkMessage.put("data", payload);

        loRaWANHandler.sendDownLinkMessage(downlinkMessage.toString());
    }
}
