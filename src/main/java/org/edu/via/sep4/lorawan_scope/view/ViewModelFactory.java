package org.edu.via.sep4.lorawan_scope.view;

import org.edu.via.sep4.lorawan_scope.model.ModelFactory;
import org.edu.via.sep4.lorawan_scope.view.downlink_view.DownlinkViewModel;
import org.edu.via.sep4.lorawan_scope.view.main_view.MainViewModel;
import org.edu.via.sep4.lorawan_scope.view.uplink_view.UplinkViewModel;
public class ViewModelFactory {
    private final ModelFactory modelFactory;
    private UplinkViewModel uplinkViewModel;
    private DownlinkViewModel downlinkViewModel;

    private MainViewModel mainViewModel;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory=modelFactory;
    }

    public UplinkViewModel getUplinkViewModel() {
        if (uplinkViewModel == null) {
            uplinkViewModel = new UplinkViewModel(modelFactory);
        }
        return uplinkViewModel;
    }

    public DownlinkViewModel getDownlinkViewModel() {
        if (downlinkViewModel == null) {
            downlinkViewModel = new DownlinkViewModel(modelFactory);
        }
        return downlinkViewModel;
    }

    public MainViewModel getMainViewModel() {
        if (mainViewModel == null) {
            mainViewModel = new MainViewModel(modelFactory);
        }
        return mainViewModel;
    }
}
