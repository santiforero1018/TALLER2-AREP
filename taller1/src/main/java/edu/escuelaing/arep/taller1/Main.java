package edu.escuelaing.arep.taller1;

import java.io.IOException;

import edu.escuelaing.arep.taller1.Facade.WebServer;

import com.google.gson.*;

/**
 * Main class to start the application
 * @author Santiago Forero Yate
 */
public class Main {

    /**
     * Default Constructor
     */
    public Main(){
         
    }

    /**
     * main void to start the application
     * @param args arguements to start the applicationthrows IOException if something fails
     */
    public static void main(String[] args) {
        try {
            WebServer.startSever();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}