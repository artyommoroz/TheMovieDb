package com.frost.themoviedb.presentation.presenter;


import com.frost.themoviedb.App;
import com.frost.themoviedb.Screens;
import com.frost.themoviedb.database.DatabaseManager;
import com.frost.themoviedb.network.ApiManager;
import com.frost.themoviedb.network.model.DetailedMovie;
import com.frost.themoviedb.network.model.DetailedMovieContainer;
import com.frost.themoviedb.presentation.view.FavoriteMoviesView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class FavoriteMoviesPresenter extends BasePresenter<FavoriteMoviesView> {

    @Inject
    ApiManager apiManager;
    @Inject
    DatabaseManager databaseManager;
    @Inject
    Router router;

    public FavoriteMoviesPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void loadFavoriteMovies() {
        getViewState().showProgress(true);
        List<DetailedMovie> detailedMovies = databaseManager.getFavoriteMovies();
        if (!detailedMovies.isEmpty()) {
            getViewState().setMovies(detailedMovies);
        } else {
            getViewState().showEmptyView(true);
        }
        getViewState().showProgress(false);
    }

    public void switchToDetailedMovieScreen(long movieId) {
        router.navigateTo(Screens.MOVIE_SCREEN, new DetailedMovieContainer(movieId, true));
    }
}
