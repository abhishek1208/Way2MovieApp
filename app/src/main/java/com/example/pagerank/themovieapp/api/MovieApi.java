package com.example.pagerank.themovieapp.api;



import com.example.pagerank.themovieapp.model.MovieItemDetails;
import com.example.pagerank.themovieapp.model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {



    @GET("3/genre/{genre_id}/movies?api_key=9ee088a6d3ed11d3c10ee27466d39427")
    Call<MovieList> getMovies(@Path("genre_id") int genre_id);


    @GET("3/movie/{movie_id}?api_key=9ee088a6d3ed11d3c10ee27466d39427")
    Call<MovieItemDetails> getMovieDetails(@Path("movie_id") int movie_id);

    @GET("3/search/movie?api_key=9ee088a6d3ed11d3c10ee27466d39427")
    Call<MovieList> searchMovie(@Query("query") String query);


}
