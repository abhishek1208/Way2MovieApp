package com.example.pagerank.themovieapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.pagerank.themovieapp.adapters.MovieListAdapter;
import com.example.pagerank.themovieapp.api.APIService;
import com.example.pagerank.themovieapp.model.GenreType;
import com.example.pagerank.themovieapp.model.MovieList;
import com.facebook.login.LoginManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<GenreType> listOfGenre;
    RecyclerView recyclerView;
    FloatingSearchView mSearchView;
    public static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listOfGenre = GenreType.getArrayList(MainActivity.this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_genrelist);
        GenreListAdapter adapter = new GenreListAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        mSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //get suggestions based on newQuery

                //pass them on to the search view
//                mSearchView.swapSuggestions(newSuggestions);
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                String query=mSearchView.getQuery();
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,SearchableActivity.class);
                i.putExtra("query",query);
                startActivity(i);
//                APIService.getMovieApi().getMovies(query).enqueue(new Callback<MovieList>() {
//                    @Override
//                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
//                        movieList = response.body();
//                        Log.d("check", "onResponse: "+ response.raw());
//                        Log.e("Success", String.valueOf(movieList.getResults().size()));
//                        recyclerView.setLayoutManager(new GridLayoutManager(GenreMovieList.this, 2));
//                        recyclerView.setAdapter(new MovieListAdapter(movieList.getResults(),GenreMovieList.this));
//                    }
//
//                    @Override
//                    public void onFailure(Call<MovieList> call, Throwable t) {
//                        Log.e("Error", "error");
//                    }
//                });

            }
        });


        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        LoginManager.getInstance().logOut();
                        Log.d(TAG, "onOptionsItemSelected: " + "loggingout");
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);

                }
            }
        });

    }


    public class GenreViewHolder extends RecyclerView.ViewHolder {

        public GenreViewHolder(View itemView) {
            super(itemView);
        }

        LinearLayout linearLayout;
        TextView tv;

    }

    public class GenreListAdapter extends RecyclerView.Adapter<GenreViewHolder> {

        @Override
        public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = getLayoutInflater();
            View convertView = li.inflate(R.layout.layout_list_item, parent, false);
            GenreViewHolder gnv = new GenreViewHolder(convertView);
            gnv.tv = (TextView) convertView.findViewById(R.id.tv_genre);
            gnv.linearLayout = (LinearLayout) convertView.findViewById(R.id.layout_linear_item);
            return gnv;

        }

        @Override
        public void onBindViewHolder(GenreViewHolder holder, int position) {
            int pos = position;
            final GenreType genreType = listOfGenre.get(pos);
            holder.tv.setText(genreType.getName());
            Drawable drawablePic = getResources().getDrawable(genreType.getImageId());
//            drawablePic.setColorFilter(0x76ffffff, PorterDuff.Mode.SRC_OVER;
            holder.linearLayout.setBackground(drawablePic);
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, GenreMovieList.class);
                    Log.d("idcheck", "onClick: " + genreType.getId());
                    i.putExtra("idOfGenre", genreType.getId());
                    startActivity(i);

                }
            });


        }

        @Override
        public int getItemCount() {
            return listOfGenre.size();
        }
    }


}
