package org.example;

import org.example.Exceptions.InvalidRequestException;
import org.example.Http.HttpRequest;
import org.example.Http.HttpResponse;
import org.example.controllers.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class Server {

    int PORT;
    List<Controller> controllers;

    public Server(int port) {
        this.PORT = port;
        controllers = new ArrayList<>();
        resolveControllers();
    }


    private void resolveControllers() {
        controllers.add(new DownloadController());
        controllers.add(new FaviconController());
    }


    public void run() throws IOException {
        ServerSocket ss = new ServerSocket(PORT);

        while (true) {
            Socket socket = ss.accept();

            byte[] rawResponse;
            try {
                HttpRequest request = new HttpRequest(socket.getInputStream());

                Controller controller = getController(request);
                HttpResponse response = controller.serve(request);
                rawResponse = response.getResponse();
            }
            catch (Exception e) {
                rawResponse = buildErrorResponse(e);
            }

            OutputStream out = socket.getOutputStream();
            out.write(rawResponse);
            out.close();
        }
    }

    private byte[] buildErrorResponse(Exception e) {
        String message;
        int statusCode;

        if (e instanceof InvalidRequestException) {
            message = e.getMessage();
            statusCode = 400;
        }
        else {
            message = "Server error";
            statusCode = 500;
        }

        String httpResponse = "HTTP/1.1 " + statusCode + "OK\r\n\r\n<html>" + message + "</html>";
        return httpResponse.getBytes(StandardCharsets.UTF_8);
    }


    private Controller getController(HttpRequest request) throws InvalidRequestException {
        for (Controller controller : controllers) {
            if (controller.getUri().equals(request.getUri()) &&
                    controller.getMethod().equals(request.getMethod())) {
                return controller;
            }
        }
        throw new InvalidRequestException("No controller mapping found", new Exception());
    }

}
