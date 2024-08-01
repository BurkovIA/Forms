package org.example;

import org.example.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        server.registerHandler("/messages", new MessagesHandler());
        server.start();
    }
}