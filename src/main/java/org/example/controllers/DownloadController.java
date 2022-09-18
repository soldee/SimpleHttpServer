package org.example.controllers;

import org.example.Http.HttpRequest;
import org.example.Http.HttpResponse;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class DownloadController implements Controller {

    private final String method;
    private final String uri;

    public DownloadController() {
        method = "GET";
        uri = "/hello";
    }

    public HttpResponse serve(HttpRequest request) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html; charset=UTF-8");

        String body = "<html><h1>Hello</h1><html>";

        return new HttpResponse(headers, body.getBytes(StandardCharsets.UTF_8), 200);
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public StringBuffer getBody() {
        return null;
    }
}
