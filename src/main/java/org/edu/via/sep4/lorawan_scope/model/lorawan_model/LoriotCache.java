package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

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

            String jason = "{\"cmd\": \"cq\", \"page\": 1, \"perPage\": 20}";
            System.out.println(jason);
            loriotHandler.sendDownLinkMessage(jason);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
