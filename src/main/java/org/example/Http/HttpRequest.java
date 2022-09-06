package org.example.Http;

import org.example.Exceptions.InvalidRequestException;

import java.io.*;
import java.util.HashMap;


public class HttpRequest {

    private String method;
    private String uri;
    private HashMap<String, String> headers;
    private String httpVersion;
    private StringBuffer body;

    public HttpRequest(InputStream in) throws InvalidRequestException {
        headers = new HashMap<>();
        body = new StringBuffer();
        parseRequest(in);
    }


    private void parseRequest(InputStream rawRequest) throws InvalidRequestException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(rawRequest));
            // First line -> POST / HTTP/1.1\r\n
            String line = in.readLine();
            System.out.println(line);
            String[] first = line.split(" ");
            this.method = first[0];
            this.uri = first[1];
            this.httpVersion = first[2];

            // Headers -> Host: localhost\r\nConnection: keep-alive\r\n\r\n
            line = in.readLine();
            while (!line.isEmpty()) {
                String[] header = line.split(":");
                headers.put(header[0].trim(), header[1].trim());
                line = in.readLine();
            }

            if (!method.equals("GET")) {
                // Body
                line = in.readLine();
                while (line != null) {
                    body.append(line).append("\r\n");
                    line = in.readLine();
                }
            }
        } catch (Exception e) {
            throw new InvalidRequestException("Error when parsing request", e);
        }
    }


    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public StringBuffer getBody() {
        return body;
    }

}

