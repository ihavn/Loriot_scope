package org.edu.via.sep4.lorawan_scope.core;

import org.edu.via.sep4.lorawan_scope.model.downlink_model.DownlinkModel;
import org.edu.via.sep4.lorawan_scope.model.downlink_model.DownlinkModelImpl;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.LoRaWANHandler;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.LoRaWANHandlerImpl;
import org.edu.via.sep4.lorawan_scope.model.uplink_model.UplinkModel;
import org.edu.via.sep4.lorawan_scope.model.uplink_model.UplinkModelImpl;

public class ModelFactory {
    private UplinkModel uplinkModel;
    private DownlinkModel downlinkModel;
    private LoRaWANHandler loRaWANHandler;

    public UplinkModel getUplinkModel() {
        if(uplinkModel == null) uplinkModel = new UplinkModelImpl();
        return uplinkModel;
    }

    public DownlinkModel getDownlinkModel() {
        if(downlinkModel == null) downlinkModel = new DownlinkModelImpl();
        return downlinkModel;
    }

    public LoRaWANHandler getLoRaWANHandler() {
        if(loRaWANHandler == null) loRaWANHandler = new LoRaWANHandlerImpl();
        return loRaWANHandler;
    }
}

