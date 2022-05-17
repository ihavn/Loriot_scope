package org.edu.via.sep4.lorawan_scope.core;

import org.edu.via.sep4.lorawan_scope.model.LoRaWANMessageHandlerModel;
import org.edu.via.sep4.lorawan_scope.model.LoRaWANMessageHandlerModelImpl;

public class ModelFactory {
    private LoRaWANMessageHandlerModel uplinkMessageModel;
    //private DownlinkMessageModel downlinkMessageModel;

    public LoRaWANMessageHandlerModel getUplinkMessageModel() {
        if(uplinkMessageModel == null) uplinkMessageModel = new LoRaWANMessageHandlerModelImpl();
        return uplinkMessageModel;
    }
}

