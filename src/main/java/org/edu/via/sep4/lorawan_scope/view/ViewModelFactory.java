package org.edu.via.sep4.lorawan_scope.view;

import org.edu.via.sep4.lorawan_scope.model.ModelFactory;
import org.edu.via.sep4.lorawan_scope.view.main_view.UplinkViewModel;
public class ViewModelFactory {
    private final ModelFactory modelFactory;
    private UplinkViewModel uplinkViewModel;
    //private DownLinkViewModel downlinkViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory=modelFactory;
    }

    public UplinkViewModel getUplinkMessageViewModel() {
        if (uplinkViewModel == null) {
            uplinkViewModel = new UplinkViewModel(modelFactory.getUplinkModel());
        }
        return uplinkViewModel;
    }
}
