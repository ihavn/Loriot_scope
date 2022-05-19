package org.edu.via.sep4.lorawan_scope.core;

import org.edu.via.sep4.lorawan_scope.view.main_view.UplinkMessageViewModel;
public class ViewModelFactory {
    private final ModelFactory modelFactory;
    private UplinkMessageViewModel uplinkMessageViewModel;
    //private DownLinkViewModel downlinkViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory=modelFactory;
    }

    public UplinkMessageViewModel getUplinkMessageViewModel() {
        if (uplinkMessageViewModel == null) {
            uplinkMessageViewModel = new UplinkMessageViewModel(modelFactory.getUplinkMessageModel());
        }
        return uplinkMessageViewModel;
    }
}
