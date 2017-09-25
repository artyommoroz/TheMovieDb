package com.frost.themoviedb.mapper;

import com.frost.themoviedb.database.model.MovieDb;
import com.frost.themoviedb.network.model.Movie;

public class MovieMapper implements Mapper<MovieDb, Movie> {

    @Override
    public Movie convertFrom(MovieDb movieDb) {
        Movie movie = new Movie();
        movie.setId(movieDb.getId());
        movie.setTitle(movieDb.getTitle());
        movie.setPosterPath(movieDb.getPosterPath());
        movie.setOverview(movieDb.getOverview());
        movie.setReleaseDate(movieDb.getReleaseDate());
        return movie;
    }

    @Override
    public MovieDb convertTo(Movie movie) {
        MovieDb movieDb = new MovieDb();
        movieDb.setId(movie.getId());
        movieDb.setTitle(movie.getTitle());
        movieDb.setPosterPath(movie.getPosterPath());
        movieDb.setOverview(movie.getOverview());
        movieDb.setReleaseDate(movie.getReleaseDate());
        return movieDb;
    }
}
