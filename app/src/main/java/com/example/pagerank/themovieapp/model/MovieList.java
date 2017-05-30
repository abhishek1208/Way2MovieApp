package com.example.pagerank.themovieapp.model;

import java.util.List;

/**
 * Created by the-dagger on 15/10/16.
 * Java class containing the POJO for response received back from the retrofit call.
 */

public class MovieList {

    private List<SingleMovie> results;

    public List<SingleMovie> getResults() {
        return results;
    }

    public class SingleMovie {
        private String poster_path;

        private String title;

        public String getPoster_path() {
            return "http://image.tmdb.org/t/p/w500/" + poster_path;
        }

        public String getTitle() {
            return title;
        }
    }
}