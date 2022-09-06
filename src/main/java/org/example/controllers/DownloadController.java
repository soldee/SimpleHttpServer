package org.example.controllers;

import org.example.Http.HttpRequest;

public class DownloadController implements Controller {

    private String method;
    private String uri;

    public DownloadController() {
        method = "GET";
        uri = "/hola";
    }

    public String serve(HttpRequest request) {
        return "HOLA";
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
