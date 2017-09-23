package com.frost.themoviedb.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.frost.themoviedb.network.model.Movie;

import java.util.List;

public interface MoviesView extends MvpView {

    void showProgress(boolean show);

    void setMovies(List<Movie> movies);

    void showEmptyView(boolean show);
}
