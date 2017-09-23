package com.frost.themoviedb.presentation.presenter;


import com.frost.themoviedb.App;
import com.frost.themoviedb.manager.ApiManager;
import com.frost.themoviedb.presentation.view.PopularMoviesView;
import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class PopularMoviesPresenter extends BasePresenter<PopularMoviesView> {

    @Inject
    ApiManager apiManager;
    @Inject
    Router router;

    public PopularMoviesPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void loadPopularMovies(int page, String byGenres) {
        unSubscribeOnDestroy(apiManager.getPopularMovies(page, "28")
                .subscribe(response -> {
                    getViewState().setMovies(response.getMovies());
//                    if (response.isSuccess()) {
//                        List<Comment> comments = response.getComments();
//                        if (comments.isEmpty()) {
//                            getViewState().showEmptyView(true);
//                        } else {
//                            getViewState().showEmptyView(false);
//                            getViewState().setComments(comments);
//                        }
//                    }
                }, throwable -> {
                    throwable.getCause();
                }));
    }
}
