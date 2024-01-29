package edu.escuelaing.arep.taller1.Facade;

import java.net.*;

import com.google.gson.*;

import java.io.*;

/**
 * Web server class to use the web application
 * @author Santiago Forero Yate
 */
public class WebServer {

    private static final int PORT = 35000;
    private static final APIRestFacade apf = new APIRestFacade();

    /**
     * Method that start the web server
     * @throws IOException
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

            outputLine = (petition.startsWith("/film")) ? movieInfo(petition.replace("/film?name=", "")) : mainPage();

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }


    /**
     * return a html structure with some movie information
     * @param name the name of the movie
     * @return a html structure with some movie information, with some headers
     */
    private static String movieInfo(String name) {
        try {
            
            JsonObject resp = apf.searchMovie(name);
            return "HTTP/1.1 200 OK\r\n" +
                    "Content-Type:text/html; charset=utf-8\\r\\n" + //
                    "\r\n" 
                    +"<!DOCTYPE html>\r\n" + //
                    "<html lang=\"en\">\r\n" + //
                    "\r\n" + //
                    "<head>\r\n" + //
                    "    <meta charset=\"UTF-8\">\r\n" + //
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                    "    <title>Movie</title>\r\n" + //
                    "    <style>\r\n" + //
                    "        * {\r\n" + //
                    "            margin: 0;\r\n" + //
                    "            padding: 0;\r\n" + //
                    "            /* display: flex; */\r\n" + //
                    "            align-items: center;\r\n" + //
                    "            justify-content: center;\r\n" + //
                    "            /* min-height: 100vh; */\r\n" + //
                    "            font-family: 'Jost', sans-serif;\r\n" + //
                    "        }\r\n" + //
                    "    </style>\r\n" + //
                    "</head>\r\n" + //
                    "\r\n" + //
                    "<body>\r\n" + //
                    "    <h1>Title:" + resp.get("Title") + "</h1>\r\n" + //
                    "    <img src=" + resp.get("Poster") + ">\r\n" + //
                    "    <h2>Director(s):" + resp.get("Director") + "</h2>\r\n" + //
                    "    <h2>Plot:" + resp.get("Plot") + "</h2>\r\n" + //
                    "</body>\r\n" + //
                    "\r\n" + //
                    "</html>";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * method that returns the principal html page
     * 
     * @return the principal page of the application
     */
    private static String mainPage() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-Type:text/html; charset=utf-8\\r\\n" + //
                "\r\n"
                +"<!DOCTYPE html>\r\n" + //
                        "<html lang=\"en\">\r\n" + //
                        "\r\n" + //
                        "<head>\r\n" + //
                        "    <meta charset=\"UTF-8\">\r\n" + //
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                        "    <title>Movies</title>\r\n" + //
                        "    <style>\r\n" + //
                        "        * {\r\n" + //
                        "            margin: 0;\r\n" + //
                        "            padding: 0;\r\n" + //
                        "            /* display: flex; */\r\n" + //
                        "            align-items: center;\r\n" + //
                        "            justify-content: center;\r\n" + //
                        "            /* min-height: 100vh; */\r\n" + //
                        "            font-family: 'Jost', sans-serif;\r\n" + //
                        "        }\r\n" + //
                        "\r\n" + //
                        "        title {\r\n" + //
                        "            display: none;\r\n" + //
                        "        }\r\n" + //
                        "\r\n" + //
                        "\r\n" + //
                        "\r\n" + //
                        "        .container {\r\n" + //
                        "            margin-top: 17%;\r\n" + //
                        "            display: flex;\r\n" + //
                        "            flex-direction: column;\r\n" + //
                        "        }\r\n" + //
                        "\r\n" + //
                        "        .consulta {\r\n" + //
                        "            display: flex;\r\n" + //
                        "            flex-direction: row;\r\n" + //
                        "        }\r\n" + //
                        "\r\n" + //
                        "        #nombre-pelicula {\r\n" + //
                        "            margin-left: 6%;\r\n" + //
                        "            width: 75%;\r\n" + //
                        "            height: 30%;\r\n" + //
                        "        }\r\n" + //
                        "\r\n" + //
                        "        #boton-consultar {\r\n" + //
                        "            margin-left: 6%;\r\n" + //
                        "            height: 30px;\r\n" + //
                        "            width: 150px;\r\n" + //
                        "            border-radius: 25%;\r\n" + //
                        "            ;\r\n" + //
                        "        }\r\n" + //
                        "    </style>\r\n" + //
                        "</head>\r\n" + //
                        "\r\n" + //
                        "\r\n" + //
                        "<body>\r\n" + //
                        "    <div class=\"container\">\r\n" + //
                        "        <div class=\"consulta\">\r\n" + //
                        "            <form action=\"/film\">\r\n" + //
                        "                <label>Escriba el nombre de la pelicula a consultar</label>\r\n" + //
                        "                <input type=\"text\" id=\"nombre-pelicula\" pkaceholder=\"Ingrese el nombre de la pelicula\" name=\"name\">\r\n" + //
                        "                <input type=\"button\" id=\"boton-consultar\" value=\"Summit\" onclick=\"consultMovie()\">\r\n" + //
                        "            </form>\r\n" + //
                        "\r\n" + //
                        "        </div>\r\n" + //
                        "\r\n" + //
                        "        <div id=\"pelicula\">\r\n" + //
                        "\r\n" + //
                        "        </div>\r\n" + //
                        "\r\n" + //
                        "    </div>\r\n" + //
                        "\r\n" + //
                        "    <script>\r\n" + //
                        "        function consultMovie() {\r\n" + //
                        "            let nameMovie = document.getElementById(\"nombre-pelicula\").value;\r\n" + //
                        "            console.log(nameMovie);\r\n" + //
                        "            const xhttp = new XMLHttpRequest();\r\n" + //
                        "            xhttp.onload = function () {\r\n" + //
                        "                document.getElementById(\"pelicula\").innerHTML =\r\n" + //
                        "                    this.responseText;\r\n" + //
                        "            }\r\n" + //
                        "            xhttp.open(\"GET\", \"/film?name=\" + nameMovie);\r\n" + //
                        "            xhttp.send();\r\n" + //
                        "        }\r\n" + //
                        "    </script>\r\n" + //
                        "</body>\r\n" + //
                        "\r\n" + //
                        "</html>";
    }

}
