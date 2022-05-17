package org.edu.via.sep4.lorawan_scope.model.lorawan;

import org.json.JSONObject;

public record GetCache(LoRaWANHandlerImpl loRaWANHandlerImpl) implements Runnable {
    public GetCache(LoRaWANHandlerImpl loRaWANHandlerImpl) {
        this.loRaWANHandlerImpl = loRaWANHandlerImpl;
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

            System.out.println(jsonObject.toString());
            loRaWANHandlerImpl.sendDownLinkMessage(jsonObject.toString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
