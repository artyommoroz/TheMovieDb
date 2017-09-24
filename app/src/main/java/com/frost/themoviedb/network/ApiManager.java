package com.frost.themoviedb.network;


import com.frost.themoviedb.network.model.DetailedMovie;
import com.frost.themoviedb.network.response.GenresResponse;
import com.frost.themoviedb.network.response.MoviesResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiManager {

    private static final String HARDCODED_API_KEY = "fbe4e6280f6a460beaad8ebe2bc130ac";
    private static final String HARDCODED_SORT_ORDER = "popularity.des";

    private ApiService apiService;

    @Inject
    public ApiManager(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<MoviesResponse> getPopularMovies(Integer page, String byGenres) {
        return apiService.getPopularMovies(HARDCODED_API_KEY, HARDCODED_SORT_ORDER, page, byGenres).compose(applySchedulers());
    }

    public Observable<MoviesResponse> getMovies(String query) {
        return apiService.getMovies(HARDCODED_API_KEY, query).compose(applySchedulers());
    }

    public Observable<DetailedMovie> getMovie(long movieId) {
        return apiService.getMovie(movieId, HARDCODED_API_KEY).compose(applySchedulers());
    }

    public Observable<GenresResponse> getGenres() {
        return apiService.getGenres(HARDCODED_API_KEY).compose(applySchedulers());
    }

    private <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
