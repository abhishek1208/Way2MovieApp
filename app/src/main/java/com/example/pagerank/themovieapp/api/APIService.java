package com.example.pagerank.themovieapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIService {

    private static final String BASE_URL = "http://api.themoviedb.org/";

    private static MovieApi movieApi;


    /**
     * Create a new instance of Retrofit.
     */
    private APIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        /**
         * Create an implementation of the API endpoints defined by the interface.
         */
        movieApi = retrofit.create(MovieApi.class);
    }


    public static MovieApi getMovieApi() {
        if (movieApi == null) {
            new APIService();
        }
        return movieApi;
    }
}
