package org.edu.via.sep4.lorawan_scope.core;

import org.edu.via.sep4.lorawan_scope.view.lorawan_message.LoRaWANMessageViewModel;
public class ViewModelFactory {
    private final ModelFactory modelFactory;
    private LoRaWANMessageViewModel loRaWANMessageViewModel;
    //private DownLinkViewModel downlinkViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory=modelFactory;
    }

    public LoRaWANMessageViewModel getUplinkMessageViewModel() {
        if (loRaWANMessageViewModel == null) {
            loRaWANMessageViewModel = new LoRaWANMessageViewModel(modelFactory.getUplinkMessageModel());
        }
        return loRaWANMessageViewModel;
    }
}
