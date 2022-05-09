package org.edu.via.sep4.lorawan_scope.lorawan;

import org.edu.via.sep4.lorawan_scope.model.UplinkMessageModelHandler;

public class LoRaWANHandler {
    private WebsocketClient wsc;
    public LoRaWANHandler(UplinkMessageModelHandler uplinkMessageModelHandler) {
        wsc = new WebsocketClient("wss://iotnet.teracom.dk/app?token=vnoRLgAAABFpb3RuZXQudGVyYWNvbS5kazamWO1sXD3jq2ov9DGJBNA=", uplinkMessageModelHandler);
    }
}
