package org.edu.via.sep4.lorawan_scope.model;

import org.edu.via.sep4.lorawan_scope.model.lorawan_model.DownlinkModel;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.LoriotHandler;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.LoriotModel;
import org.edu.via.sep4.lorawan_scope.model.lorawan_model.UplinkModel;

public class ModelFactory {
    private LoriotHandler loriotHandler;

    public UplinkModel getUplinkModel() {
        if (loriotHandler == null) loriotHandler = new LoriotHandler();
        return loriotHandler;
    }

    public DownlinkModel getDownlinkModel() {
        if (loriotHandler == null) loriotHandler = new LoriotHandler();
        return loriotHandler;
    }

    public LoriotModel getLoriotModel() {
        if (loriotHandler == null) loriotHandler = new LoriotHandler();
        return loriotHandler;
    }
}

