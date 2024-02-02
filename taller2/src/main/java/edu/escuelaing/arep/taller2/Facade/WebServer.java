package edu.escuelaing.arep.taller2.Facade;

import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.*;

import java.io.*;

/**
 * Web server class to use the web application
 * 
 * @author Santiago Forero Yate
 */
public class WebServer {

    private static final int PORT = 35000;
    private static final APIRestFacade apf = new APIRestFacade();

    /**
     * Defautl Constructor
     */
    public WebServer() {

    }

    /**
     * Method that start the web server
     * 
     * @throws IOException throws IOException if something fails
     */
    public static void startSever() throws IOException {
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
            boolean readingFirst = true;
            String petition = "";

            while ((inputLine = in.readLine()) != null) {

                if (readingFirst) {
                    petition = inputLine.split(" ")[1];
                    readingFirst = false;
                }
                if (!in.ready()) {
                    break;
                }
            }

            System.out.println("ASi llego la peticion: " + petition);

            outputLine = (petition.startsWith("/film")) ? movieInfo(petition.replace("/film?name=", ""))
                    : mainPage(petition);

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }

    /**
     * return a html structure with some movie information
     * 
     * @param name the name of the movie
     * @return a html structure with some movie information, with some headers
     */
    private static String movieInfo(String name) {
        try {

            JsonObject resp = apf.searchMovie(name);
            JsonElement title = resp.get("Title"), poster = resp.get("Poster"), director = resp.get("Director"),
                    plot = resp.get("Plot");

            String outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type:text/html\r\n"
                    + "\r\n"
                    + getStaticFile("/movieInfo.html").replace("{Title}", title.toString())
                            .replace("\"{Poster}\"", poster.toString()).replace("{Directors}", director.toString())
                            .replace("{Plot}", plot.toString());

            return outputLine;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * method that returns the principal html page
     * 
     * @param filePetition the path where is allocate the html page and other files
     *                     that use this page
     * @return the principal page of the application
     */
    private static String mainPage(String filePetition) {

        String mimeType = getMimeType(filePetition);
        System.out.println("mimetype regresado para " + filePetition + ": " + mimeType);
        String resp = getStaticFile(filePetition);

        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type:" + mimeType + "\r\n"
                + "\r\n"
                + resp;

        return outputLine;
    }

    /**
     * Method that identify the MIME type of the files to return to the client
     * 
     * @param filePetition path of the petition
     * @return a String with the MIME type of the file
     */
    private static String getMimeType(String filePetition) {
        return (filePetition.endsWith(".html") || filePetition.endsWith("/")) ? "text/html"
                : ((filePetition.endsWith(".css")) ? "text/css"
                        : (filePetition.endsWith(".js")) ? "application/javascript" : (filePetition.endsWith(".jpg")) ? "image/jp2" : "text/plain");
    }

    /**
     * this mehtod returns the static file related with the request
     * 
     * @param filePetition path of the file
     * @return A string with all information insite the file
     */
    private static String getStaticFile(String filePetition) {
        Path file = (filePetition.equals("/")) ? Paths.get("target/classes/public/static/client.html")
                : Paths.get("target/classes/public/static" + filePetition);

        Charset charset = Charset.forName("UTF-8");
        StringBuilder outputLine = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                outputLine.append(line).append("\n");
            }
        } catch (Exception e) {
            System.err.format("IOException: "+ e.getMessage(), e);
        }

        return outputLine.toString();
    }

}
