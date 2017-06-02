package com.example.pagerank.themovieapp.db.models;

import io.realm.RealmObject;

/**
 * Created by abhishekyadav on 02/06/17.
 */

public class RealmInteger extends RealmObject {
    private int movieid;

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

//    @Override
//    public boolean equals(Object obj) {
//        RealmInteger otherObj= (RealmInteger) obj;
//        return this.movieid==otherObj.movieid;
//    }
}
