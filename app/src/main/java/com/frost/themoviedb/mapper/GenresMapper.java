package com.frost.themoviedb.mapper;


import com.frost.themoviedb.database.model.GenreDb;
import com.frost.themoviedb.network.model.Genre;

public class GenresMapper implements Mapper<GenreDb, Genre> {

    @Override
    public Genre convertFrom(GenreDb genreDb) {
        Genre genre = new Genre();
        genre.setId(genreDb.getId());
        genre.setName(genreDb.getName());
        return genre;
    }

    @Override
    public GenreDb convertTo(Genre genre) {
        GenreDb genreDb = new GenreDb();
        genreDb.setId(genre.getId());
        genreDb.setName(genre.getName());
        return genreDb;
    }
}
