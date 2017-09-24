package com.frost.themoviedb.database;


import com.frost.themoviedb.database.model.DetailedMovieDb;
import com.frost.themoviedb.mapper.DetailedMovieMapper;
import com.frost.themoviedb.network.model.DetailedMovie;

import io.realm.Realm;

public class DatabaseManager {

    private Realm realm;

    public DatabaseManager(Realm realm) {
        this.realm = realm;
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
}
