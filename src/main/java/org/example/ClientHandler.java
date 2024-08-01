package org.example;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(clientSocket.getInputStream());
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            while (in.hasNextLine()) {
                String request = in.nextLine();
                System.out.println("Received message from client: " + request);

                String path = request.split("\\s+")[1]; // Extracting the request path
                List<NameValuePair> queryParams = URLEncodedUtils.parse(path, StandardCharsets.UTF_8);

                handleRequest(path, queryParams, out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRequest(String path, List<NameValuePair> queryParams, PrintWriter out) {
        switch (path) {
            case "/messages":
                int lastParam = extractQueryParam(queryParams, "last");
                out.println("Last param value: " + lastParam);
                break;
            default:
                out.println("Invalid path");
        }
    }

    private int extractQueryParam(List<NameValuePair> queryParams, String name) {
        for (NameValuePair param : queryParams) {
            if (param.getName().equals(name)) {
                return Integer.parseInt(param.getValue());
            }
        }
        return -1;
    }
}