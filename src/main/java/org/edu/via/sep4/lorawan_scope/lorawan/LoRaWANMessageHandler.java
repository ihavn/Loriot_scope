package org.edu.via.sep4.lorawan_scope.lorawan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.edu.via.sep4.lorawan_scope.model.UplinkMessageModelHandler;
import org.edu.via.sep4.lorawan_scope.model.UplinkMessageModelImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public record LoRaWANMessageHandler(UplinkMessageModelHandler uplinkMessageModel) {

    private String tsToString(long ts) {
        Date date = new Date(ts); // convert seconds to milliseconds
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH:mm:ss.SSS"); // the format of your date
        return dateFormat.format(date);
    }

    public void unpackLoRaWANMessage(String raw) {
        JSONObject jo = new JSONObject(raw);
        if (jo.getString("cmd").equals("rx")) {
            uplinkMessageModel.addUplinkMessage(new UplinkMessageModelImpl(jo.getString("EUI"), tsToString(jo.getLong("ts")), jo.getInt("fcnt"), jo.getInt("port"), jo.getString("data")));
        } else if (jo.getString("cmd").equals("cq")) {
            JSONArray jsonArray = jo.getJSONArray("cache");
            Object item;
            for (int i = jsonArray.length() - 1; i >= 0; i--) {
                item = jsonArray.get(i);
                if (item instanceof JSONObject) {
                    jo = (JSONObject) item;
                    //System.out.printf("EUI:%s TS: %d fcnt: %d port: %d data: %s\n", jo.get("EUI"), jo.getLong("ts"), jo.getInt("fcnt"), jo.getInt("port"), jo.getString("data"));
                    try {
                        uplinkMessageModel.addUplinkMessage(new UplinkMessageModelImpl(jo.getString("EUI"), tsToString(jo.getLong("ts")), jo.getInt("fcnt"), jo.getInt("port"), jo.getString("data")));
                    } catch (JSONException e) {
                        // The keys were not found
                    }
                }
            }
        }
    }
}

