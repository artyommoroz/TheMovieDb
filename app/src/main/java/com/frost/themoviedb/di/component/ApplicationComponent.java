package com.frost.themoviedb.di.component;

import com.frost.themoviedb.di.module.ApplicationModule;
import com.frost.themoviedb.di.scopes.ApplicationScope;
import com.frost.themoviedb.presentation.presenter.ContainerPresenter;
import com.frost.themoviedb.presentation.presenter.DetailedMoviePresenter;
import com.frost.themoviedb.presentation.presenter.FavoriteMoviesPresenter;
import com.frost.themoviedb.presentation.presenter.GenresPresenter;
import com.frost.themoviedb.presentation.presenter.MainActivityPresenter;
import com.frost.themoviedb.presentation.presenter.PopularMoviesPresenter;
import com.frost.themoviedb.presentation.presenter.SearchMoviesPresenter;

import dagger.Component;

@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivityPresenter presenter);

    void inject(ContainerPresenter presenter);

    void inject(PopularMoviesPresenter presenter);

    void inject(FavoriteMoviesPresenter presenter);

    void inject(DetailedMoviePresenter presenter);

    void inject(GenresPresenter presenter);

    void inject(SearchMoviesPresenter presenter);
}
