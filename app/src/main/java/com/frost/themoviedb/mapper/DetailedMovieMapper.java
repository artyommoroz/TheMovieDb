package com.frost.themoviedb.mapper;


import com.frost.themoviedb.database.model.DetailedMovieDb;
import com.frost.themoviedb.database.model.GenreDb;
import com.frost.themoviedb.network.model.DetailedMovie;
import com.frost.themoviedb.network.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class DetailedMovieMapper implements Mapper<DetailedMovieDb, DetailedMovie> {

    @Override
    public DetailedMovie convertFrom(DetailedMovieDb detailedMovieDb) {
        DetailedMovie detailedMovie = new DetailedMovie();
        detailedMovie.setId(detailedMovieDb.getId());
        detailedMovie.setTitle(detailedMovieDb.getTitle());
        detailedMovie.setPosterPath(detailedMovieDb.getPosterPath());
        detailedMovie.setOverview(detailedMovieDb.getOverview());
        detailedMovie.setReleaseDate(detailedMovieDb.getReleaseDate());
        detailedMovie.setVoteAverage(detailedMovieDb.getVoteAverage());
        detailedMovie.setVoteCount(detailedMovieDb.getVoteCount());
        GenresMapper mapper = new GenresMapper();
        List<Genre> genres = new ArrayList<>();
        for (GenreDb genreDb : detailedMovieDb.getGenres()) {
            genres.add(mapper.convertFrom(genreDb));
        }
        detailedMovie.setGenres(genres);
        return detailedMovie;
    }

    @Override
    public DetailedMovieDb convertTo(DetailedMovie detailedMovie) {
        DetailedMovieDb detailedMovieDb = new DetailedMovieDb();
        detailedMovieDb.setId(detailedMovie.getId());
        detailedMovieDb.setTitle(detailedMovie.getTitle());
        detailedMovieDb.setPosterPath(detailedMovie.getPosterPath());
        detailedMovieDb.setOverview(detailedMovie.getOverview());
        detailedMovieDb.setReleaseDate(detailedMovie.getReleaseDate());
        detailedMovieDb.setVoteAverage(detailedMovie.getVoteAverage());
        detailedMovieDb.setVoteCount(detailedMovie.getVoteCount());
        GenresMapper mapper = new GenresMapper();
        List<GenreDb> genres = new ArrayList<>();
        for (Genre genre : detailedMovie.getGenres()) {
            genres.add(mapper.convertTo(genre));
        }
        detailedMovieDb.setGenres(genres);
        return detailedMovieDb;
    }
}
