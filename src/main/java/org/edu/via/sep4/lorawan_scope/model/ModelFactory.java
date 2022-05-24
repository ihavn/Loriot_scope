package org.edu.via.sep4.lorawan_scope.model;

import org.edu.via.sep4.lorawan_scope.model.lorawan_model.*;

public class ModelFactory {
    private LoriotHandler loriotHandler;

    public UplinkModel getUplinkModel() {
        if(loriotHandler == null) loriotHandler = new LoriotHandler();
        return loriotHandler;
    }

    public DownlinkModel getDownlinkModel() {
        if(loriotHandler == null) loriotHandler = new LoriotHandler();
        return loriotHandler;
    }

    public LoriotModel getLoriotModel() {
        if(loriotHandler == null) loriotHandler = new LoriotHandler();
        return loriotHandler;
    }
}

