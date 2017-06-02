package com.example.pagerank.themovieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.pagerank.themovieapp.MovieDetailsPage;
import com.example.pagerank.themovieapp.R;
import com.example.pagerank.themovieapp.model.MovieList;
import com.github.florent37.picassopalette.PicassoPalette;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter for populating the recyclerView for movie list
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private List<MovieList.SingleMovie> singleMovieList;
    Context context;

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    /**
     * Constructor which initializes the list of movies with the response received from the API call.
     */
    public MovieListAdapter(List<MovieList.SingleMovie> singleMovieList, Context context) {
        this.singleMovieList = singleMovieList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(singleMovieList.get(position).getTitle());
        Picasso.with(context).setLoggingEnabled(true);
        Picasso.with(context).load(singleMovieList.get(position).getPoster_path()).fit().placeholder(R.mipmap.ic_placeholder)
                .into(holder.posterImage, PicassoPalette.with(singleMovieList.get(position).getPoster_path(), holder.posterImage)
                        .use(PicassoPalette.Profile.VIBRANT_LIGHT)
                        .intoBackground(holder.movieTitle)
                );
        holder.viewCached.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, MovieDetailsPage.class);
                i.putExtra("movie_id",singleMovieList.get(position).getId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return singleMovieList.size();
    }

    /**
     * Holder which defines the layout for a single item in the recyclerView
     */
    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImage;
        TextView movieTitle;
        View viewCached;

        MovieViewHolder(View view) {
            super(view);
            viewCached=view;
            posterImage = (ImageView) view.findViewById(R.id.moviePoster);
            movieTitle = (TextView) view.findViewById(R.id.movieTitle);
        }
    }
}
