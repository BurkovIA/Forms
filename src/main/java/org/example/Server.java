package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int PORT = 12345;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public Server() {
        this.handlers = handlers;
        this.handlers = new HashMap<>();
        threadPool = Executors.newFixedThreadPool(64);
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Server started on port " + PORT);
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                threadPool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, RequestHandler> handlers;

    public void registerHandler(String path, RequestHandler handler) {
        handlers.put(path, handler);
    }


}
