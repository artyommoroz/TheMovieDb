package com.frost.themoviedb.network.response;


import com.frost.themoviedb.network.model.DetailedMovie;

public class MovieResponse {

    private DetailedMovie movie;

    public DetailedMovie getMovie() {
        return movie;
    }

    public void setMovie(DetailedMovie movie) {
        this.movie = movie;
    }
}
