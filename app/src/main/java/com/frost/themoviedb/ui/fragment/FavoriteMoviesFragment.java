package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.presentation.presenter.FavoriteMoviesPresenter;
import com.frost.themoviedb.presentation.view.FavoriteMoviesView;

public class FavoriteMoviesFragment extends BaseFragment implements FavoriteMoviesView {

    @InjectPresenter
    FavoriteMoviesPresenter presenter;

    public static FavoriteMoviesFragment newInstance() {
        FavoriteMoviesFragment fragment = new FavoriteMoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_favorite_movies;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
