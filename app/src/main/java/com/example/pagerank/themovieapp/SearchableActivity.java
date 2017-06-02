package com.example.pagerank.themovieapp;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pagerank.themovieapp.adapters.MovieListAdapter;
import com.example.pagerank.themovieapp.api.APIService;
import com.example.pagerank.themovieapp.model.MovieList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchableActivity extends AppCompatActivity {
    MovieList movieList;
    RecyclerView recyclerView;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        recyclerView= (RecyclerView) findViewById(R.id.rView_search);
        layout= (LinearLayout) findViewById(R.id.layout_sorry);
        String query = getIntent().getStringExtra("query");
        fetchMovies(query);


    }

    private void fetchMovies(String param) {
        APIService.getMovieApi().searchMovie(param).enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                movieList = response.body();
                Log.d("check", "onResponse: " + response.raw());
                Log.e("Success", String.valueOf(movieList.getResults().size()));
                if(movieList.getResults().size()==0){
                    Toast.makeText(SearchableActivity.this, "0 size", Toast.LENGTH_SHORT).show();
                    layout.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                }
                else{
                recyclerView.setLayoutManager(new GridLayoutManager(SearchableActivity.this, 2));
                recyclerView.setAdapter(new MovieListAdapter(movieList.getResults(), SearchableActivity.this));}
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.e("Error", "error");
            }
        });
    }


}