package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import org.json.JSONObject;

public class LoriotCache implements Runnable {
    private final LoriotHandler loriotHandler;

    public LoriotCache(LoriotHandler loriotHandler) {
        this.loriotHandler = loriotHandler;
        Thread t = new Thread(this, "getCash");
        t.start();
    }

    @Override
    public void run() {
        try {
            // Wait for websocket to be completely connected
            Thread.sleep(1000);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cmd", "cq");
            jsonObject.put("page", 1);
            jsonObject.put("perPage", 20);

            System.out.println(jsonObject);
            loriotHandler.sendDownLinkMessage(jsonObject.toString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
