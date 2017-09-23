package com.frost.themoviedb.network;


import com.frost.themoviedb.network.response.GenresResponse;
import com.frost.themoviedb.network.response.MovieItemsResponse;
import com.frost.themoviedb.network.response.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("discover/movie")
    Observable<MovieItemsResponse> getPopularMovies(@Query("page") Integer page,
                                                    @Query("by_genres") String byGenres);

    @GET("search/movie")
    Observable<MovieItemsResponse> getMovies(@Query("query") String query);

    @GET("movie")
    Observable<MovieResponse> getMovie(@Query("movie_id") long movieId);

    @GET("genre/movie/list")
    Observable<GenresResponse> getGenres();
}
