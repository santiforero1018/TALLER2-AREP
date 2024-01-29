package edu.escuelaing.arep.taller1;

import java.io.IOException;

import edu.escuelaing.arep.taller1.Facade.WebServer;

/**
 * Main class to start the application
 * @author Santiago Forero Yate
 */
public class Main {
    public static void main(String[] args) {
        try {
            WebServer.startSever();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}