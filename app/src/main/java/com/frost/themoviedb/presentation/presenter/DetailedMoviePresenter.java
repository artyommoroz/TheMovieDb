package com.frost.themoviedb.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.frost.themoviedb.App;
import com.frost.themoviedb.database.DatabaseManager;
import com.frost.themoviedb.network.ApiManager;
import com.frost.themoviedb.network.model.DetailedMovie;
import com.frost.themoviedb.network.model.Movie;
import com.frost.themoviedb.presentation.view.DetailedMovieView;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class DetailedMoviePresenter extends BasePresenter<DetailedMovieView> {

    @Inject
    ApiManager apiManager;
    @Inject
    DatabaseManager databaseManager;
    @Inject
    Router router;

    public DetailedMoviePresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void loadMovie(long movieId, boolean isFavorite) {
        if (isFavorite) {
            getViewState().setMovie(databaseManager.getDetailedMovie(movieId));
        } else {
            unSubscribeOnDestroy(apiManager.getMovie(movieId)
                    .subscribe(response -> {
                        getViewState().setMovie(response);
                    }, throwable -> getViewState().onError(throwable.toString())));
        }
    }

    public void addToFavorites(DetailedMovie movie) {
        movie.setFavorite(true);
        databaseManager.addDetailedMovieToFavorites(movie);
        getViewState().onMovieAddedToFavorites();
    }

    public void deleteFromFavorites(long movieId) {
        databaseManager.deleteDetailedMovieFromFavorites(movieId);
        getViewState().onMovieDeletedFromFavorites();
    }

    public void onBackPressed() {
        router.exit();
    }
}
