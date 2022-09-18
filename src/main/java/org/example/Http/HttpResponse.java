package org.example.Http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class HttpResponse {

    private byte[] response;
    private final HashMap<String, String> headers;
    private final byte[] body;
    private final int statusCode;

    public HttpResponse(HashMap<String, String> headers, byte[] body, int stautsCode) {
        this.headers = headers;
        this.body = body;
        this.statusCode = stautsCode;
    }

    public byte[] getResponse() throws IOException {
        buildResponse();
        return response;
    }

    private void buildResponse() throws IOException {
        String responseString = "HTTP/1.1 " + statusCode + " OK\r\n";

        for (String header : headers.keySet()) {
            String value = headers.get(header);
            responseString += header + ": " + value + "\r\n";
        }
        responseString += "\r\n";

        ByteArrayOutputStream out = new ByteArrayOutputStream( );
        out.write(responseString.getBytes(StandardCharsets.UTF_8));
        out.write(body);

        response = out.toByteArray();
    }

}
