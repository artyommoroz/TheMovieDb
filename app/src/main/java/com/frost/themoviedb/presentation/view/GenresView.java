package com.frost.themoviedb.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.frost.themoviedb.network.model.Genre;

import java.util.List;

public interface GenresView extends MvpView {

    void showProgress(boolean show);

    void setGenres(List<Genre> genres);
}
