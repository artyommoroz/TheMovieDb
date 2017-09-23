package com.frost.themoviedb.network.model;


import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
}
