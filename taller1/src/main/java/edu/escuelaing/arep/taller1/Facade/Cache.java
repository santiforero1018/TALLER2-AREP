package edu.escuelaing.arep.taller1.Facade;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private ConcurrentHashMap<String, String> movieCache;
    private APIRestFacade apf;
    private static Cache cache = null;


    /**
     * Cache's class constructor
     * @param apf
     */
    public Cache(APIRestFacade apf){
        this.apf = apf;
        movieCache = new ConcurrentHashMap<String, String>();
    }

    /**
     * method that returns the instance of Cache class
     * @return the current instance of cache
     */
    public static Cache getInstance(){
        if(cache == null){
            cache = new Cache(new APIRestFacade());
        }

        return cache;
    }


    /**
     * method that returns a result if this one exist inside chache, else
     * do a petition to the external API
     * 
     * @param name name of the movie to search
     * @return All data of the movie
     */
    public String getMovie(String name){
        if(movieCache.containsKey(name)){
            return movieCache.get(name);
        }
        try{
            movieCache.putIfAbsent(name, apf.searchMovie(name));
            return movieCache.get(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * method that returns all the movies storaged in cache
     * @return a concurrent HashMap with all movies stored 
     */
    public ConcurrentHashMap<String,String> getMoviesInCache(){
        return movieCache;
    }
}
