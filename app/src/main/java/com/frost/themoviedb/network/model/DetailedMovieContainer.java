package com.frost.themoviedb.network.model;

public class DetailedMovieContainer {

    private long movieId;
    private boolean isFavorite;

    public DetailedMovieContainer(long movieId, boolean isFavorite) {
        this.movieId = movieId;
        this.isFavorite = isFavorite;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
