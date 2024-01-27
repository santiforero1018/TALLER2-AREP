package edu.escuelaing.arep.taller1.Facade;

import java.net.*;
import java.io.*;

public class WebServer {

    private static final int PORT = 35000;
    private static final Cache cache = Cache.getInstance();

    // Start service method
    public static void startSever() throws IOException{
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            String movieName = "";
            while ((inputLine = in.readLine()) != null) {
                inputLine = inputLine.replace("GET /name=", "");
                inputLine = inputLine.replace(" HTTP/1.1", "");
                movieName = inputLine;
            }

            if (!in.ready()) {
                break;
            }

            outputLine = "HTTP/1.1 200 \r\n" +
                    "Content-Type: application/json \r\n" +
                    "Access-Control-Allow-Origin: * \r\n" +
                    "\r\n" +
                    cache.getMovie(movieName.replace("GET /?name=", ""));

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }

}
