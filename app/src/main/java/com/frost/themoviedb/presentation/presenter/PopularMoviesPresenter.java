package com.frost.themoviedb.presentation.presenter;


import com.frost.themoviedb.App;
import com.frost.themoviedb.Screens;
import com.frost.themoviedb.database.DatabaseManager;
import com.frost.themoviedb.network.ApiManager;
import com.frost.themoviedb.network.model.DetailedMovieContainer;
import com.frost.themoviedb.presentation.view.PopularMoviesView;
import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class PopularMoviesPresenter extends BasePresenter<PopularMoviesView> {

    @Inject
    ApiManager apiManager;
    @Inject
    DatabaseManager databaseManager;
    @Inject
    Router router;

    public PopularMoviesPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void loadPopularMovies(boolean isNetworkAvailable, int page, String withGenres) {
        if (isNetworkAvailable) {
            getViewState().showProgress(true);
            unSubscribeOnDestroy(apiManager.getPopularMovies(page, withGenres)
                    .doOnNext(response -> databaseManager.addMovies(response.getMovies()))
                    .subscribe(response -> {
                        getViewState().showProgress(false);
                        getViewState().setMovies(response.getMovies());
                    }, throwable -> {
                        getViewState().showProgress(false);
                        getViewState().onError(throwable.toString());
                    }));
        } else {
            getViewState().setMovies(databaseManager.getMovies());
        }
    }

    public void loadMorePopularMovies(int page, String withGenres) {
        apiManager.getPopularMovies(page, withGenres)
                .doOnNext(response -> databaseManager.addMovies(response.getMovies()))
                .subscribe(response -> {
                    getViewState().addMovies(response.getMovies());
                }, throwable -> {
                    getViewState().onError(throwable.toString());
                });
    }

    public void switchToDetailedMovieScreen(long movieId) {
        router.navigateTo(Screens.MOVIE_SCREEN, new DetailedMovieContainer(movieId, false));
    }
}
