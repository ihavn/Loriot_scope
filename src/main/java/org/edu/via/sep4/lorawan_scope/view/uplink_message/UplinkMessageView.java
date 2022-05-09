package org.edu.via.sep4.lorawan_scope.view.uplink_message;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UplinkMessageView {
    private StringProperty devEUI = new SimpleStringProperty();
    private StringProperty localTime = new SimpleStringProperty();
    private IntegerProperty fcntUp = new SimpleIntegerProperty();
    private IntegerProperty port = new SimpleIntegerProperty();
    private StringProperty payload = new SimpleStringProperty();

    public UplinkMessageView(String devEUI, String localTime, int fcntUp, int port, String payload) {
        this.devEUI.setValue(devEUI);
        this.localTime.setValue(localTime);
        this.fcntUp.setValue(fcntUp);
        this.port.setValue(port);
        this.payload.setValue(payload);
    }

    public StringProperty getDevEUIProperty() {
        return devEUI;
    }

    public StringProperty getLocalTimeProperty() {
        return localTime;
    }

    public IntegerProperty getFcntUpProperty() {
        return fcntUp;
    }

    public IntegerProperty getPortProperty() {
        return port;
    }

    public StringProperty getPayloadProperty() {
        return payload;
    }
}