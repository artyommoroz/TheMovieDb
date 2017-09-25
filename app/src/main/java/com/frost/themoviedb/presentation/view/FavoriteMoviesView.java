package com.frost.themoviedb.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.frost.themoviedb.network.model.DetailedMovie;
import com.frost.themoviedb.network.model.Movie;

import java.util.List;

public interface FavoriteMoviesView extends MvpView {

    void showProgress(boolean show);

    void setMovies(List<DetailedMovie> detailedMovies);

    void showEmptyView(boolean show);
}
