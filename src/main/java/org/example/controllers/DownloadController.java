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
        System.out.println("controller");
        return "HOLA";
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public StringBuffer getBody() {
        return null;
    }
}
