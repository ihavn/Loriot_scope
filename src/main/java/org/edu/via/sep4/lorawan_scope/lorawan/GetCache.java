package org.edu.via.sep4.lorawan_scope.lorawan;

import org.json.JSONObject;

public record GetCache(LoRaWANHandler loRaWANHandler) implements Runnable {
    public GetCache(LoRaWANHandler loRaWANHandler) {
        this.loRaWANHandler = loRaWANHandler;
        Thread t = new Thread(this, "getCash");
        t.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cmd", "cq");
            jsonObject.put("page", 1);
            jsonObject.put("perPage", 20);

            System.out.println(jsonObject.toString(4));
            loRaWANHandler.sendDownLinkMessage(jsonObject.toString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
