package org.edu.via.sep4.lorawan_scope.view.lorawan_message;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoRaWANMessageView {
    private final StringProperty devEUI = new SimpleStringProperty();
    private final StringProperty localTime = new SimpleStringProperty();
    private final StringProperty fcntUp = new SimpleStringProperty();
    private final StringProperty port = new SimpleStringProperty();
    private final StringProperty payload = new SimpleStringProperty();

    public LoRaWANMessageView(String devEUI, String localTime, String fcntUp, String port, String payload) {
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

    public StringProperty getFcntUpProperty() {
        return fcntUp;
    }

    public StringProperty getPortProperty() {
        return port;
    }

    public StringProperty getPayloadProperty() {
        return payload;
    }
}
