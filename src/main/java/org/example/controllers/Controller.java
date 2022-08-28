package org.example.controllers;

import org.example.Http.HttpRequest;

public interface Controller {

    public String getMethod();

    public String getUri();

    public StringBuffer getBody();

    public String serve(HttpRequest request);
}
