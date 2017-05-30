package com.example.pagerank.themovieapp.model;

import com.example.pagerank.themovieapp.R;

import java.util.ArrayList;

/**
 * Created by abhishekyadav on 30/05/17.
 */

public class GenreType {

    private int id;
    private int imageId;
    private String name;


    public int getId() {
        return id;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public GenreType(int id, int imageId, String name) {
        this.id = id;
        this.imageId = imageId;
        this.name = name;
    }

    public static ArrayList<GenreType> getArrayList(){
    ArrayList<GenreType> retVal=new ArrayList<>();
        retVal.add(new GenreType(R.string.action_id,R.drawable.action,"Action"));
        retVal.add(new GenreType(R.string.adventure_id,R.drawable.adventure,"Adventure"));
        retVal.add(new GenreType(R.string.animation_id,R.drawable.comedy,"Comedy"));
        retVal.add(new GenreType(R.string.crime_id,R.drawable.crime,"Crime"));
        retVal.add(new GenreType(R.string.documentary_id,R.drawable.documentary,"Documentary"));
        retVal.add(new GenreType(R.string.drama_id,R.drawable.drama,"Drama"));
        retVal.add(new GenreType(R.string.family_id,R.drawable.family,"Family"));
        retVal.add(new GenreType(R.string.fantasy_id,R.drawable.fantasy,"Fantasy"));
        retVal.add(new GenreType(R.string.history_id,R.drawable.history,"History"));
        retVal.add(new GenreType(R.string.horror_id,R.drawable.horror,"Horror"));
        retVal.add(new GenreType(R.string.music_id,R.drawable.music,"Music"));
        retVal.add(new GenreType(R.string.mystery_id,R.drawable.mystery,"Mystery"));
        retVal.add(new GenreType(R.string.romance_id,R.drawable.romance,"Romance"));
        retVal.add(new GenreType(R.string.thriller_id,R.drawable.thriller,"Thriller"));
        retVal.add(new GenreType(R.string.tv_movie_id,R.drawable.tv_movies,"Tv Movies"));
        retVal.add(new GenreType(R.string.war_id,R.drawable.war,"War"));
        retVal.add(new GenreType(R.string.western_id,R.drawable.western,"Western"));


        return retVal;

    }
}
