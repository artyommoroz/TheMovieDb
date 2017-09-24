package com.frost.themoviedb.presentation.presenter;


import com.frost.themoviedb.App;
import com.frost.themoviedb.Screens;
import com.frost.themoviedb.network.ApiManager;
import com.frost.themoviedb.presentation.view.MoviesView;
import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class SearchMoviesPresenter extends BasePresenter<MoviesView> {

    @Inject
    ApiManager apiManager;
    @Inject
    Router router;

    public SearchMoviesPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void loadMovies(String query) {
        unSubscribeOnDestroy(apiManager.getMovies(query)
                .subscribe(response -> {
                    getViewState().setMovies(response.getMovies());
                }, throwable -> {
                    throwable.getCause();
                }));
    }

    public void switchToDetailedMovieScreen(long movieId) {
        router.navigateTo(Screens.MOVIE_SCREEN, movieId);
    }

    public void onBackPressed() {
        router.exit();
    }
}
