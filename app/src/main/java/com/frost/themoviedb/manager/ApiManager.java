package com.frost.themoviedb.manager;


import com.frost.themoviedb.network.ApiService;
import com.frost.themoviedb.network.response.GenresResponse;
import com.frost.themoviedb.network.response.MovieItemsResponse;
import com.frost.themoviedb.network.response.MovieResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiManager {

    private ApiService apiService;

    @Inject
    public ApiManager(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<MovieItemsResponse> getPopularMovies(Integer page, String byGenres) {
        return apiService.getPopularMovies(page, byGenres).compose(applySchedulers());
    }

    public Observable<MovieItemsResponse> getMovies(String query) {
        return apiService.getMovies(query).compose(applySchedulers());
    }

    public Observable<MovieResponse> getMovie(long movieId) {
        return apiService.getMovie(movieId).compose(applySchedulers());
    }

    public Observable<GenresResponse> getGenres() {
        return apiService.getGenres().compose(applySchedulers());
    }

    private <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
