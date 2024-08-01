package org.example;

class MessagesHandler implements RequestHandler {
    @Override
    public void handle(Request request, Response response) {
        String path = request.getPath();
        if (path.equals("/messages")) {
            String lastQueryParam = request.getQueryParam("last");
            response.send("Last param value: " + lastQueryParam);
        } else {
            response.send("Invalid path");
        }
    }
}