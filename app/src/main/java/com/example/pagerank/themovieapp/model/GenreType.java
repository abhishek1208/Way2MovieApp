package com.example.pagerank.themovieapp.model;

import android.content.Context;

import com.example.pagerank.themovieapp.R;

import java.util.ArrayList;

/**
 * Created by abhishekyadav on 30/05/17.
 */

public class GenreType {

    private int id;
    private int imageId;
    private String name;
    static Context context;


    public int getId() {
        return context.getResources().getInteger(id);
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

    public static ArrayList<GenreType> getArrayList(Context ct){
        context=ct;
    ArrayList<GenreType> retVal=new ArrayList<>();
        retVal.add(new GenreType(R.integer.action_id,R.drawable.action,"Action"));
        retVal.add(new GenreType(R.integer.adventure_id,R.drawable.adventure,"Adventure"));
        retVal.add(new GenreType(R.integer.animation_id,R.drawable.comedy,"Comedy"));
        retVal.add(new GenreType(R.integer.crime_id,R.drawable.crime,"Crime"));
        retVal.add(new GenreType(R.integer.documentary_id,R.drawable.documentary,"Documentary"));
        retVal.add(new GenreType(R.integer.drama_id,R.drawable.drama,"Drama"));
        retVal.add(new GenreType(R.integer.family_id,R.drawable.family,"Family"));
        retVal.add(new GenreType(R.integer.fantasy_id,R.drawable.fantasy,"Fantasy"));
        retVal.add(new GenreType(R.integer.history_id,R.drawable.history,"History"));
        retVal.add(new GenreType(R.integer.horror_id,R.drawable.horror,"Horror"));
        retVal.add(new GenreType(R.integer.music_id,R.drawable.music,"Music"));
        retVal.add(new GenreType(R.integer.mystery_id,R.drawable.mystery,"Mystery"));
        retVal.add(new GenreType(R.integer.romance_id,R.drawable.romance,"Romance"));
        retVal.add(new GenreType(R.integer.thriller_id,R.drawable.thriller,"Thriller"));
        retVal.add(new GenreType(R.integer.tv_movie_id,R.drawable.tv_movies,"Tv Movies"));
        retVal.add(new GenreType(R.integer.war_id,R.drawable.war,"War"));
        retVal.add(new GenreType(R.integer.western_id,R.drawable.western,"Western"));


        return retVal;

    }
}
