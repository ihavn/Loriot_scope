package org.edu.via.sep4.lorawan_scope.lorawan;

import org.edu.via.sep4.lorawan_scope.model.UplinkMessageModelHandler;

public class LoRaWANHandler {
    private WebsocketClient wsc;
    public LoRaWANHandler(UplinkMessageModelHandler uplinkMessageModelHandler) {
        LoRaWANMessageHandler loRaWANMessageHandler = new LoRaWANMessageHandler(uplinkMessageModelHandler);
        wsc = new WebsocketClient("wss://iotnet.teracom.dk/app?token=vnoRLgAAABFpb3RuZXQudGVyYWNvbS5kazamWO1sXD3jq2ov9DGJBNA=", loRaWANMessageHandler);
    }

    public void sendDownLinkMessage(String json) {
        wsc.sendDownLink(json);
    }
}
