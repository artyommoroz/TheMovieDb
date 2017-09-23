package com.frost.themoviedb.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.frost.themoviedb.network.model.Movie;

import java.util.List;

public interface PopularMoviesView extends MvpView {

    void setMovies(List<Movie> movies);
}
