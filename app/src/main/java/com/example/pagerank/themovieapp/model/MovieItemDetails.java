package com.example.pagerank.themovieapp.model;

/**
 * Created by abhishekyadav on 01/06/17.
 */

public class MovieItemDetails {
    private String title;
    private  String poster_path;
    private float vote_count;
    private float popularity;

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return "http://image.tmdb.org/t/p/w500/"+poster_path;
    }

    public float getVote_count() {
        return vote_count;
    }

    public float getPopularity() {
        return popularity;
    }
}
