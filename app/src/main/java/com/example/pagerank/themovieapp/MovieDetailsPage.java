package com.example.pagerank.themovieapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pagerank.themovieapp.adapters.MovieListAdapter;
import com.example.pagerank.themovieapp.api.APIService;
import com.example.pagerank.themovieapp.db.models.FacebookUser;
import com.example.pagerank.themovieapp.db.utils.FacebookUserFuns;
import com.example.pagerank.themovieapp.model.BookTicketActivity;
import com.example.pagerank.themovieapp.model.MovieItemDetails;
import com.example.pagerank.themovieapp.model.MovieList;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.github.florent37.picassopalette.PicassoPalette;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsPage extends AppCompatActivity {
    TextView tv_popularity, tv_vote, tv_title;
    ImageView poster, like_btn;
    String movie_id;
    MovieItemDetails movie_item;
    LoginManager loginManager;
    boolean checkForToggle;
    public static final String TAG = "MovieDetailsPage";
    String userId;
    TextView btn_tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details_page);
        tv_popularity = (TextView) findViewById(R.id.tv_popularity);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_vote = (TextView) findViewById(R.id.tv_vote);
        like_btn = (ImageView) findViewById(R.id.like_btn);
        poster = (ImageView) findViewById(R.id.iv_poster);
        movie_id = getIntent().getStringExtra("movie_id");
        fetchMovieDetails(Integer.valueOf(movie_id));
        loginManager = LoginManager.getInstance();
        btn_tickets= (TextView) findViewById(R.id.btn_book_tickets);

        btn_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MovieDetailsPage.this, BookTicketActivity.class);
                startActivity(i);
            }
        });
        setLikeButton();

        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkForToggle) {
                    checkForToggle = !checkForToggle;
                    FacebookUserFuns.updateUser(userId, Integer.valueOf(movie_id));
                    setLikeButton();
                    Toast.makeText(getApplicationContext(), "Movie is liked", Toast.LENGTH_SHORT).show();
                } else {
                    checkForToggle = !checkForToggle;
                    FacebookUserFuns.removeMovieId(userId, Integer.valueOf(movie_id));
                    setLikeButton();
                    Toast.makeText(getApplicationContext(), "Movie is not liked", Toast.LENGTH_SHORT).show();



                }
            }
        });


    }

    private void setLikeButton() {
        checkForUser();
        if (checkForToggle) {

            like_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_like_btn));

        } else {
            like_btn.setImageDrawable(getResources().getDrawable(R.drawable.ic_dislike_button));

        }
    }

    private boolean checkForUser() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            userId = accessToken.getUserId();
            Log.d(TAG, "checkForUser: the movie id ina ctivity passes is"+ movie_id);
            checkForToggle = FacebookUserFuns.checkForMovieId(userId, Integer.valueOf(movie_id));
            return checkForToggle;
        } else {
            userId = "guest";
            checkForToggle = FacebookUserFuns.checkForMovieId("guest", Integer.valueOf(movie_id));
            return checkForToggle;
        }
    }

    private void fetchMovieDetails(int param) {
        APIService.getMovieApi().getMovieDetails(param).enqueue(new Callback<MovieItemDetails>() {
            @Override
            public void onResponse(Call<MovieItemDetails> call, Response<MovieItemDetails> response) {
                movie_item = response.body();
                Context context = MovieDetailsPage.this;
                Log.d("check", "onResponse: " + response.raw());
                Log.e("Success", String.valueOf(movie_item));
                tv_title.setText(movie_item.getTitle());
                Picasso.with(context).setLoggingEnabled(true);
                Picasso.with(context).load(movie_item.getPoster_path()).fit().placeholder(R.mipmap.ic_placeholder)
                        .into(poster, PicassoPalette.with(movie_item.getPoster_path(), poster)
                                .use(PicassoPalette.Profile.VIBRANT_LIGHT)
                                .intoBackground(tv_title)
                        );
                tv_vote.setText(String.valueOf(movie_item.getVote_count()));
                tv_popularity.setText(String.valueOf(movie_item.getPopularity()));

            }

            @Override
            public void onFailure(Call<MovieItemDetails> call, Throwable t) {
                Log.e("Error", "error");
            }
        });
    }
}
