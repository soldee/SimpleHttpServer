package org.example;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.*;
import org.example.Exceptions.InvalidRequestException;


class Main {

    public static void main(String[] args) throws InvalidRequestException, IOException {

        // Load properties
        Config config = ConfigFactory.load();
        int PORT = config.getInt("application.port");

        // Start server
        Server server = new Server(PORT);
        server.run();
    }
}