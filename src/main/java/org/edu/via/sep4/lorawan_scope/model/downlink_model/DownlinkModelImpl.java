package org.edu.via.sep4.lorawan_scope.model.downlink_model;

import org.edu.via.sep4.lorawan_scope.model.ModelFactory;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.LoRaWANHandler;
import org.json.JSONObject;

public class DownlinkModelImpl implements DownlinkModel {
    private final LoRaWANHandler loRaWANHandler;

    public DownlinkModelImpl(ModelFactory modelFactory) {
        this.loRaWANHandler = modelFactory.getLoRaWANHandler();
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
