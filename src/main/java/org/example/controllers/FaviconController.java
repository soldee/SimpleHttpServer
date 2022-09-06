package org.example.controllers;

import org.apache.commons.io.IOUtils;
import org.example.Http.HttpRequest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class FaviconController implements Controller {

    private final String method;
    private final String uri;

    public FaviconController() {
        method = "GET";
        uri = "/favicon.ico";
    }

    public byte[] serve(HttpRequest request) {
        System.out.println("favicon");
        try {
            FileInputStream favicon = new FileInputStream("src/main/resources/favicon.ico");
            return IOUtils.toByteArray(favicon);
        }
        catch (FileNotFoundException e) {
            return "".getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
