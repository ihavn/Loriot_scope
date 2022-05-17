package org.edu.via.sep4.lorawan_scope.model.lorawan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.edu.via.sep4.lorawan_scope.model.LoRaWANMessageHandlerModel;
import org.edu.via.sep4.lorawan_scope.model.UplinkMessageDataModelImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public record LoRaWANMessageHandler(LoRaWANMessageHandlerModel uplinkMessageModel) {

    private String tsToString(long ts) {
        Date date = new Date(ts); // convert seconds to milliseconds
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss.SSS"); // the format of your date
        return dateFormat.format(date);
    }

    public void unpackLoRaWANMessage(String raw) {
        JSONObject jo = new JSONObject(raw);
        if (jo.getString("cmd").equals("rx")) {
            uplinkMessageModel.addUplinkMessage(new UplinkMessageDataModelImpl(jo.getString("EUI"), tsToString(jo.getLong("ts")), Integer.toString(jo.getInt("fcnt")), Integer.toString(jo.getInt("port")), jo.getString("data")));
        } else if (jo.getString("cmd").equals("cq")) {
            JSONArray jsonArray = jo.getJSONArray("cache");
            Object item;
            for (int i = jsonArray.length() - 1; i >= 0; i--) {
                item = jsonArray.get(i);
                if (item instanceof JSONObject) {
                    jo = (JSONObject) item;
                    try {
                        uplinkMessageModel.addUplinkMessage(new UplinkMessageDataModelImpl(jo.getString("EUI"), tsToString(jo.getLong("ts")), Integer.toString(jo.getInt("fcnt")), Integer.toString(jo.getInt("port")), jo.getString("data")));
                    } catch (JSONException e) {
                        System.out.println();
                    }
                }
            }
        } else if (jo.getString("cmd").equals("tx")) {
            if (jo.has("success")) {
                uplinkMessageModel.addUplinkMessage(new UplinkMessageDataModelImpl(jo.getString("EUI"), "", "", "", jo.getString("data") + " (Enqueued for sending)"));
            } else {
                uplinkMessageModel.addUplinkMessage(new UplinkMessageDataModelImpl(jo.getString("EUI"), "", "", "", jo.getString("error")));
            }
        } else if (jo.getString("cmd").equals("txd")) {
            uplinkMessageModel.addUplinkMessage(new UplinkMessageDataModelImpl(jo.getString("EUI"), tsToString(jo.getLong("ts")), "", "", 	"(Enqueued data sent)"));
        }
    }

    public void websocketConnected() {
        uplinkMessageModel.websocketConnected();
    }
}

