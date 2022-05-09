package org.edu.via.sep4.lorawan_scope.core;

import org.edu.via.sep4.lorawan_scope.view.uplink_message.UplinkMessageViewModel;
public class ViewModelFactory {
    private ModelFactory modelFactory;
    private UplinkMessageViewModel uplinkMessageViewModel;
    //privat DownLinkViewModel downlinkViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory=modelFactory;
    }

    public UplinkMessageViewModel getUplinkMessageViewModel() {
        if (uplinkMessageViewModel == null) {
            uplinkMessageViewModel = new UplinkMessageViewModel(modelFactory.getUplinkMessageModel());
        }
        return uplinkMessageViewModel;
    }
/*
    public DownlinkMessageViewModel getDownlinkMessageViewModel() {
        if (downlinkMessageViewModel == null) {
            downlinkMessageViewModel = new DownlinkMessageViewModel(modelFactory.getDownlinkMessageModel());
        }
        return downlinkMessageViewModel;
    }
 */
}
