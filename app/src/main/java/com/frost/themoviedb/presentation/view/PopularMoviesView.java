package com.frost.themoviedb.presentation.view;

import com.frost.themoviedb.network.model.Movie;

import java.util.List;

public interface PopularMoviesView extends MoviesView {

    void addMovies(List<Movie> movies);
}
