package org.edu.via.sep4.lorawan_scope.model.lorawan_model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class WebSocketClient implements WebSocket.Listener {
    private WebSocket server;
    private final LoriotHandler loriotHandler;
    private List<CharSequence> parts = new ArrayList<>();
    private CompletableFuture<?> accumulatedMessage = new CompletableFuture<>();

    // Send down-link message to device
    // Must be in Json format according to https://github.com/ihavn/IoT_Semester_project/blob/master/LORA_NETWORK_SERVER.md
    public void sendDownLinkMessage(String jsonTelegram) {
        server.sendText(jsonTelegram, true);
    }

    // E.g. url: "wss://iotnet.teracom.dk/app?token=??????????????????????????????????????????????="
    // Substitute ????????????????? with the token you have been given
    public WebSocketClient(LoriotHandler loriotHandler) {
        this.loriotHandler = loriotHandler;

    }

    public void connectToWebSocket(String url) {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create(url), this);
        server = ws.join();
    }

    //onOpen()
    public void onOpen(WebSocket webSocket) {
        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
        webSocket.request(1);
        System.out.println("WebSocket Listener has been opened for requests.");
        loriotHandler.websocketConnected();
    }

    //onError()
    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println("A " + error.getCause() + " exception was thrown.");
        System.out.println("Message: " + error.getLocalizedMessage());
        webSocket.abort();
    }

    //onClose()
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.out.println("WebSocket closed!");
        System.out.println("Status:" + statusCode + " Reason: " + reason);
        return new CompletableFuture().completedFuture("onClose() completed.").thenAccept(System.out::println);
    }

    //onPing()
    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Ping: Client ---> Server");
        System.out.println(message.asCharBuffer());
        return new CompletableFuture().completedFuture("Ping completed.").thenAccept(System.out::println);
    }

    //onPong()
    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Pong: Client ---> Server");
        System.out.println(message.asCharBuffer());
        return new CompletableFuture().completedFuture("Pong completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onText(WebSocket webSocket, CharSequence message, boolean last) {
        parts.add(message);
        webSocket.request(1);
        if (last) {
            System.out.println(String.join("", parts));  // Deligate to LaRaWANMessageHandler
            loriotHandler.unpackLoRaWANMessage(String.join("", parts));
            parts = new ArrayList<>();
            accumulatedMessage.complete(null);
            CompletionStage<?> cf = accumulatedMessage;
            accumulatedMessage = new CompletableFuture<>();
            return cf;
        }
        return accumulatedMessage;
    }
}

