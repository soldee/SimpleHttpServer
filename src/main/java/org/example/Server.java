package org.example;

import org.example.Exceptions.InvalidRequestException;
import org.example.Http.HttpRequest;
import org.example.controllers.*;

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
    }


    public void run() throws IOException, InvalidRequestException {
        ServerSocket ss = new ServerSocket(PORT);

        while (true) {
            Socket socket = ss.accept();
            HttpRequest request = new HttpRequest(socket.getInputStream());

            byte[] rawResponse;
            try {
                Controller controller = getController(request);
                String response = controller.serve(request);
                rawResponse = buildResponse(response);
            }
            catch (InvalidRequestException e) {
                rawResponse = buildErrorResponse(e.getMessage());
            }
            OutputStream out = socket.getOutputStream();
            out.write(rawResponse);
            out.close();
        }
    }

    private byte[] buildErrorResponse(String response) {
        String httpResponse = "HTTP/1.1 400 OK\r\n\r\n<html>" + response + "</html>";
        return httpResponse.getBytes(StandardCharsets.UTF_8);
    }


    private byte[] buildResponse(String response) {
        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n<html>" + response + "</html>";
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
