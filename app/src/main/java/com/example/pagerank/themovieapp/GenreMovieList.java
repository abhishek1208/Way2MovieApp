package com.example.pagerank.themovieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.pagerank.themovieapp.adapters.MovieListAdapter;
import com.example.pagerank.themovieapp.api.APIService;
import com.example.pagerank.themovieapp.model.MovieList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreMovieList extends AppCompatActivity {
    MovieList movieList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_movie_list);
        recyclerView = (RecyclerView) findViewById(R.id.rView);
        fetchMovies(getIntent().getIntExtra("idOfGenre",28));
    }


    private void fetchMovies(int param) {
        APIService.getMovieApi().getMovies(param).enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                movieList = response.body();
                Log.d("check", "onResponse: "+ response.raw());
                Log.e("Success", String.valueOf(movieList.getResults().size()));
                recyclerView.setLayoutManager(new GridLayoutManager(GenreMovieList.this, 2));
                recyclerView.setAdapter(new MovieListAdapter(movieList.getResults(),GenreMovieList.this));
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.e("Error", "error");
            }
        });
    }
}
