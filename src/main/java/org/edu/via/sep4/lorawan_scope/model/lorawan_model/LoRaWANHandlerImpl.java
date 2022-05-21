package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import org.edu.via.sep4.lorawan_scope.model.ModelFactory;
import org.edu.via.sep4.lorawan_scope.model.uplink_model.UplinkData;

public class LoRaWANHandlerImpl implements LoRaWANHandler {
    private WebsocketClient websocketClient;
    private final LoRaWANMessageHandler loRaWANMessageHandler;
    private UplinkData uplinkData;

    public LoRaWANHandlerImpl(ModelFactory modelFactory) {
        loRaWANMessageHandler = modelFactory.getLoRaWANHandler();
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
