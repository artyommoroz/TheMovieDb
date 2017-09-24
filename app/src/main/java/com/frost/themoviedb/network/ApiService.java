package com.frost.themoviedb.network;


import com.frost.themoviedb.network.model.DetailedMovie;
import com.frost.themoviedb.network.response.GenresResponse;
import com.frost.themoviedb.network.response.MoviesResponse;
import com.frost.themoviedb.network.response.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("discover/movie")
    Observable<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey,
                                                @Query("sort_by") String sortBy,
                                                @Query("page") Integer page,
                                                @Query("with_genres") String withGenres);

    @GET("search/movie")
    Observable<MoviesResponse> getMovies(@Query("api_key") String apiKey,
                                         @Query(value = "query", encoded = true) String query);

    @GET("movie/{id}")
    Observable<DetailedMovie> getMovie(@Path(value = "id", encoded = true) long movieId,
                                       @Query("api_key") String apiKey);


    @GET("genre/movie/list")
    Observable<GenresResponse> getGenres(@Query("api_key") String apiKey);
}
