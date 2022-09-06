package org.example.controllers;

import org.example.Http.HttpRequest;

public interface Controller {

    String getMethod();

    String getUri();

    StringBuffer getBody();

    Object serve(HttpRequest request);
}
