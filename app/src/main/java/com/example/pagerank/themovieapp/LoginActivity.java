package com.example.pagerank.themovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pagerank.themovieapp.db.models.FacebookUser;
import com.example.pagerank.themovieapp.db.models.RealmInteger;
import com.example.pagerank.themovieapp.db.utils.FacebookUserFuns;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {
    LoginButton loginButton;
    CallbackManager callbackManager;
    Realm realm;
    Button guest;
    public static final String TAG="LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(isLoggedIn()){
            Intent i=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }
        realm=Realm.getDefaultInstance();
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();
        guest= (Button) findViewById(R.id.btn_guest);



        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FacebookUserFuns.addUser("guest");
                Log.d(TAG, "onClick: "+ FacebookUserFuns.getUserByiId("guest").getMovieIdList());

                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


        ///
//        RealmInteger realmInteger=new RealmInteger();
//        realmInteger.setMovieid(233);
//        RealmList<RealmInteger> list=new RealmList<>();
//        list.add(realmInteger);
//        FacebookUserFuns.addUser("1473738159313203",list);
//        Log.d(TAG, "onCreate: "+getMovieListString(FacebookUserFuns.getUserByiId("1473738159313203").getMovieIdList()));
//        FacebookUserFuns.updateUser("1473738159313203",1208);



        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("id check", "onSuccess: "+loginResult.getAccessToken().getUserId());
                FacebookUserFuns.addUser(loginResult.getAccessToken().getUserId());
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Sign in cancelled!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }


    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public ArrayList<Integer> getMovieListString(RealmList<RealmInteger> movieIdList){
        ArrayList<Integer> retVal=new ArrayList<>();
        for(RealmInteger realmInteger:movieIdList){
            retVal.add(realmInteger.getMovieid());
        }
        return retVal;

    }
}
