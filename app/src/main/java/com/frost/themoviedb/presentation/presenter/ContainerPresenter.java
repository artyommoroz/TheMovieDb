package com.frost.themoviedb.presentation.presenter;


import com.arellomobile.mvp.InjectViewState;
import com.frost.themoviedb.App;
import com.frost.themoviedb.Screens;
import com.frost.themoviedb.network.model.Genre;
import com.frost.themoviedb.presentation.view.ContainerView;

import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class ContainerPresenter extends BasePresenter<ContainerView> {

    @Inject
    Router router;

    public ContainerPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void convertGenreNamesToString(List<Genre> genres) {
        if (genres != null) {
            StringBuilder genresString = new StringBuilder();
            for (Genre genre : genres) {
                genresString.append(genre.getName()).append(" ");
            }
            getViewState().setToolbarSubtitle(genresString.toString());
        }
    }

    public void convertGenreIdsToString(List<Genre> genres) {
        if (genres != null) {
            StringBuilder genresString = new StringBuilder();
            for (Genre genre : genres) {
                genresString.append(genre.getId()).append(",");
            }
            // Remove last comma
            genresString.substring(0, genresString.length() - 1);
            getViewState().setQuery(genresString.toString());
        } else {
            getViewState().setQuery(null);
        }
    }

    public void switchToGenresScreen() {
        router.navigateTo(Screens.GENRES_SCREEN);
    }

    public void switchToSearchMoviesScreen() {
        router.navigateTo(Screens.SEARCH_MOVIES_SCREEN);
    }
}
