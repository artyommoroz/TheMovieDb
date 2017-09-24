package com.frost.themoviedb.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.frost.themoviedb.network.model.DetailedMovie;

public interface DetailedMovieView extends MvpView {

    void setMovie(DetailedMovie movie);

    void onMovieAddedToFavorites();

    void onMovieDeletedFromFavorites();
}
