package org.edu.via.sep4.lorawan_scope.core;

import org.edu.via.sep4.lorawan_scope.model.UplinkMessageModelHandler;
import org.edu.via.sep4.lorawan_scope.model.UplinkMessageModelHandlerImpl;

public class ModelFactory {
    private UplinkMessageModelHandler uplinkMessageModel;
    //private DownlinkMessageModel downlinkMessageModel;

    public UplinkMessageModelHandler getUplinkMessageModel() {
        if(uplinkMessageModel == null) uplinkMessageModel = new UplinkMessageModelHandlerImpl();
        return uplinkMessageModel;
    }
}

