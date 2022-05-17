package org.edu.via.sep4.lorawan_scope.model.lorawan;

import org.edu.via.sep4.lorawan_scope.model.UplinkMessageDataModel;
import org.edu.via.sep4.lorawan_scope.model.LoRaWANMessageHandlerModel;

public class LoRaWANHandlerImpl implements LoRaWANHandler {
    private WebsocketClient websocketClient;
    private LoRaWANMessageHandler loRaWANMessageHandler;
    private UplinkMessageDataModel uplinkMessageDataModel;

    public LoRaWANHandlerImpl(LoRaWANMessageHandlerModel loRaWANMessageHandlerModel) {
        loRaWANMessageHandler = new LoRaWANMessageHandler(loRaWANMessageHandlerModel);
    }

    public void connectToWebSocket(String url) {
        websocketClient = new WebsocketClient(loRaWANMessageHandler);
        websocketClient.connectToWebSocket(url);
        GetCache getCash = new GetCache(this);
    }

    public void sendDownLinkMessage(String json) {
        websocketClient.sendDownLink(json);
    }
}
