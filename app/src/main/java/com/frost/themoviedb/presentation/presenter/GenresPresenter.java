package com.frost.themoviedb.presentation.presenter;


import com.frost.themoviedb.App;
import com.frost.themoviedb.Screens;
import com.frost.themoviedb.network.ApiManager;
import com.frost.themoviedb.network.model.Genre;
import com.frost.themoviedb.presentation.view.GenresView;
import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class GenresPresenter extends BasePresenter<GenresView> {

    @Inject
    ApiManager apiManager;
    @Inject
    Router router;

    public GenresPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void loadGenres() {
        unSubscribeOnDestroy(apiManager.getGenres()
                .subscribe(response -> {
                    List<Genre> genres = response.getGenres();
                    getViewState().setGenres(genres);
                }, throwable -> {
                    throwable.getCause();
                }));
    }

    public void applyCheckedGenres(List<Genre> genres) {
        router.newRootScreen(Screens.CONTAINER_SCREEN, genres);
    }
}
