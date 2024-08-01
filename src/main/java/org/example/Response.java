package org.example;

import java.io.PrintWriter;

class Response {
    private PrintWriter out;

    public Response(PrintWriter out) {
        this.out = out;
    }

    public void send(String message) {
        out.println(message);
    }
}