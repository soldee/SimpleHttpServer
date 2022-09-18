package org.example.controllers;

import org.example.Http.HttpRequest;
import org.example.Http.HttpResponse;

public interface Controller {

    String getMethod();

    String getUri();

    StringBuffer getBody();

    HttpResponse serve(HttpRequest request) throws Exception;
}
