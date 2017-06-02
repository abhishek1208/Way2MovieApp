package com.example.pagerank.themovieapp.db.models;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by abhishekyadav on 02/06/17.
 */

public class FacebookUser extends RealmObject{

    @PrimaryKey
    private String id;
    private RealmList<RealmInteger> movieIdList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<RealmInteger> getMovieIdList() {
        return movieIdList;
    }

    public void setMovieIdList(RealmList<RealmInteger> movieIdList) {
        this.movieIdList = movieIdList;
    }


}
