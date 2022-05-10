package org.edu.via.sep4.lorawan_scope.lorawan;

import org.json.JSONObject;
import org.edu.via.sep4.lorawan_scope.model.UplinkMessageModelHandler;
import org.edu.via.sep4.lorawan_scope.model.UplinkMessageModelImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoRaWANMessageHandler {
    private UplinkMessageModelHandler uplinkMessageModel;

    public LoRaWANMessageHandler(UplinkMessageModelHandler uplinkMessageModel) {
        this.uplinkMessageModel = uplinkMessageModel;
    }

    private String tsToString(long ts) {
        Date date = new Date(ts); // convert seconds to milliseconds
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss.SSS"); // the format of your date
        return dateFormat.format(date);
    }
    public void unpackLoRaWANMessage(String raw) {
        JSONObject jsonObject = new JSONObject(raw.toString());
        if (jsonObject.getString("cmd").equals("rx")) {
            uplinkMessageModel.addUplinkMessage(new UplinkMessageModelImpl(jsonObject.getString("EUI"), tsToString(jsonObject.getLong("ts")), jsonObject.getInt("fcnt"), jsonObject.getInt("port"), jsonObject.getString("data")));
        }
    }
}
