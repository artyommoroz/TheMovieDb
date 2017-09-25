package com.frost.themoviedb.database;


import com.frost.themoviedb.database.model.DetailedMovieDb;
import com.frost.themoviedb.database.model.MovieDb;
import com.frost.themoviedb.mapper.DetailedMovieMapper;
import com.frost.themoviedb.mapper.MovieMapper;
import com.frost.themoviedb.network.model.DetailedMovie;
import com.frost.themoviedb.network.model.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class DatabaseManager {

    private Realm realm;

    public DatabaseManager(Realm realm) {
        this.realm = realm;
    }

    public void addMovies(List<Movie> movies) {
        realm.beginTransaction();
        MovieMapper mapper = new MovieMapper();
        for (int i = 0; i < movies.size(); i++) {
            realm.copyToRealmOrUpdate(mapper.convertTo(movies.get(i)));
        }
        realm.commitTransaction();
    }

    public List<Movie> getMovies() {
        realm.beginTransaction();
        List<MovieDb> moviesDb = realm.where(MovieDb.class).findAll();
        realm.commitTransaction();
        List<Movie> detailedMovies = new ArrayList<>();
        MovieMapper mapper = new MovieMapper();
        for (int i = 0; i < moviesDb.size(); i++) {
            detailedMovies.add(mapper.convertFrom(moviesDb.get(i)));
        }
        return detailedMovies;
    }

    public void addDetailedMovieToFavorites(DetailedMovie movie) {
        realm.beginTransaction();
        DetailedMovieMapper mapper = new DetailedMovieMapper();
        realm.copyToRealmOrUpdate(mapper.convertTo(movie));
        realm.commitTransaction();
    }

    public void deleteDetailedMovieFromFavorites(long movieId) {
        realm.beginTransaction();
        DetailedMovieDb detailedMovieDb = realm.where(DetailedMovieDb.class)
                .equalTo("id", movieId).findFirst();
        detailedMovieDb.deleteFromRealm();
        realm.delete(DetailedMovieDb.class);
        realm.commitTransaction();
    }

    public DetailedMovie getDetailedMovie(long movieId) {
        realm.beginTransaction();
        DetailedMovieDb detailedMovieDb = realm.where(DetailedMovieDb.class)
                .equalTo("id", movieId).findFirst();
        realm.commitTransaction();
        DetailedMovieMapper mapper = new DetailedMovieMapper();
        return mapper.convertFrom(detailedMovieDb);
    }

    public List<DetailedMovie> getFavoriteMovies() {
        realm.beginTransaction();
        List<DetailedMovieDb> detailedMoviesDb = realm.where(DetailedMovieDb.class).findAll();
        realm.commitTransaction();
        List<DetailedMovie> detailedMovies = new ArrayList<>();
        DetailedMovieMapper mapper = new DetailedMovieMapper();
        for (int i = 0; i < detailedMoviesDb.size(); i++) {
            detailedMovies.add(mapper.convertFrom(detailedMoviesDb.get(i)));
        }
        return detailedMovies;
    }
}
