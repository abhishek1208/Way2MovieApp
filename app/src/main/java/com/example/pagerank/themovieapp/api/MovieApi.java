package com.example.pagerank.themovieapp.api;



import com.example.pagerank.themovieapp.model.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieApi {

    /**
     *
     * @param type "popular" for a list of popular movies and "top_rated" for a list of top rated movies.
     */

    @GET("3/movie/{type}?api_key=9ee088a6d3ed11d3c10ee27466d39427")
    Call<MovieList> getMovies(@Path("type") String type);

}
