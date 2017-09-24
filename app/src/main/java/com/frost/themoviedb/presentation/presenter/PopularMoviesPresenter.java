package com.frost.themoviedb.presentation.presenter;


import com.frost.themoviedb.App;
import com.frost.themoviedb.Screens;
import com.frost.themoviedb.network.ApiManager;
import com.frost.themoviedb.presentation.view.PopularMoviesView;
import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class PopularMoviesPresenter extends BasePresenter<PopularMoviesView> {

    @Inject
    ApiManager apiManager;
    @Inject
    Router router;

    public PopularMoviesPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void loadPopularMovies(int page, String withGenres) {
        getViewState().showProgress(true);
        unSubscribeOnDestroy(apiManager.getPopularMovies(page, withGenres)
                .subscribe(response -> {
                    getViewState().showProgress(false);
                    getViewState().setMovies(response.getMovies());
                }, throwable -> {
                    getViewState().showProgress(false);
                    throwable.getCause();
                }));
    }

    public void switchToDetailedMovieScreen(long movieId) {
        router.navigateTo(Screens.MOVIE_SCREEN, movieId);
    }
}
