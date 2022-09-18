package org.example.controllers;

import org.apache.commons.io.IOUtils;
import org.example.Http.HttpRequest;
import org.example.Http.HttpResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class FaviconController implements Controller {

    private final String method;
    private final String uri;

    public FaviconController() {
        method = "GET";
        uri = "/favicon.ico";
    }

    public HttpResponse serve(HttpRequest request) {
        HttpResponse response;
        try {
            FileInputStream favicon = new FileInputStream("src/main/resources/favicon.ico");
            byte[] body = IOUtils.toByteArray(favicon);

            HashMap<String,String> headers = new HashMap<>();
            headers.put("Accept-Ranges", "bytes");
            headers.put("Content-Length", String.valueOf(body.length));
            headers.put("Content-Type", "image/x-icon");

            response = new HttpResponse(headers, body, 200);
        }
        catch (Exception e) {
            byte[] body = "Error getting resource: favicon.ico".getBytes(StandardCharsets.UTF_8);
            response = new HttpResponse(new HashMap<>(), body, 500);
        }
        return response;
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
