package com.example.pagerank.themovieapp.db.utils;

import android.util.Log;

import com.example.pagerank.themovieapp.db.models.FacebookUser;
import com.example.pagerank.themovieapp.db.models.RealmInteger;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static com.facebook.login.widget.ProfilePictureView.TAG;

/**
 * Created by abhishekyadav on 02/06/17.
 */

public class FacebookUserFuns {

    private static Realm realm;

    static {
        realm = Realm.getDefaultInstance();
    }


    public static void addUser(String userId) {
        realm.beginTransaction();
        RealmQuery query = realm.where(FacebookUser.class).equalTo("id", userId);
        RealmResults results = query.findAll();
        realm.commitTransaction();
        if (results.size() == 0) {
            realm.beginTransaction();
            FacebookUser fbuser = new FacebookUser();
            fbuser.setId(userId);
            realm.copyToRealmOrUpdate(fbuser);
            realm.commitTransaction();
        }


    }

    public static void addUserWithList(String userId, RealmList<RealmInteger> list) {

        realm.beginTransaction();
        FacebookUser fbuser = new FacebookUser();
        if (userId != null) {
            fbuser.setId(userId);
        }
        if (list != null) {
            fbuser.setMovieIdList(list);
        }
        realm.copyToRealmOrUpdate(fbuser);
        realm.commitTransaction();
        Log.d(TAG, "addUser: " + getMovieListString(fbuser.getMovieIdList()));

    }

    public static void updateUser(String userId, int movieId) {
        RealmInteger realmInteger = new RealmInteger();
        realmInteger.setMovieid(movieId);
        RealmList<RealmInteger> list;

        realm.beginTransaction();
        RealmQuery query = realm.where(FacebookUser.class).equalTo("id", userId);
        RealmResults results = query.findAll();
        realm.commitTransaction();

        if (results.size() == 0) {
            realm.beginTransaction();
            FacebookUser fbuser = new FacebookUser();
            fbuser.setId(userId);


            list = new RealmList<>();
            list.add(realmInteger);
            fbuser.setMovieIdList(list);
            realm.copyToRealmOrUpdate(fbuser);
            realm.commitTransaction();


        } else {
            FacebookUser fbuser = (FacebookUser) results.first();
            list = fbuser.getMovieIdList();

            Log.d(TAG, "updateUser: " + list + " ," + list.contains(realmInteger));

            for (RealmInteger counter : list) {
                if (counter.getMovieid() == realmInteger.getMovieid()) {
                    return;
                }
            }


            realm.beginTransaction();
            list.add(realmInteger);
            Log.d(TAG, "updateUser: " + getMovieListString(fbuser.getMovieIdList()));

            realm.commitTransaction();


            Log.d(TAG, "updateUser: " + getMovieListString(fbuser.getMovieIdList()));


        }
    }

    public static void removeMovieId(String userId, int movieId) {
        RealmInteger realmInteger = new RealmInteger();
        realmInteger.setMovieid(movieId);
        RealmList<RealmInteger> list;

        realm.beginTransaction();
        RealmQuery query = realm.where(FacebookUser.class).equalTo("id", userId);
        RealmResults results = query.findAll();
        realm.commitTransaction();

        if (results.size() == 0) {
            return;

        } else {
            realm.beginTransaction();
            FacebookUser fbuser = (FacebookUser) results.first();
            list = fbuser.getMovieIdList();

            Log.d(TAG, "removeUser: " + list + " ," + list.contains(realmInteger));

            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i).getMovieid() == realmInteger.getMovieid()) {
                    list.remove(i);
                }
            }

            realm.commitTransaction();


            Log.d(TAG, "removeUser: " + getMovieListString(fbuser.getMovieIdList()));


        }

    }

    public static FacebookUser getUserByiId(String userId) {
        realm.beginTransaction();
        FacebookUser fbuser = realm.where(FacebookUser.class).equalTo("id", userId).findFirst();
        realm.commitTransaction();
        return fbuser;
    }

    public static ArrayList<Integer> getMovieListString(RealmList<RealmInteger> movieIdList) {
        ArrayList<Integer> retVal = new ArrayList<>();
        for (RealmInteger realmInteger : movieIdList) {
            retVal.add(realmInteger.getMovieid());
        }
        return retVal;

    }

    public static boolean checkForMovieId(String userId, int movieId) {
        FacebookUser fbUser = getUserByiId(userId);
        RealmInteger realmInteger = new RealmInteger();
        realmInteger.setMovieid(movieId);
        if (fbUser == null) {
            return false;
        }
        for (RealmInteger counter : fbUser.getMovieIdList()) {
            if (counter.getMovieid() == realmInteger.getMovieid()) {
                return true;
            }
        }

        return false;

    }
}
