package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.presentation.presenter.MoviePresenter;
import com.frost.themoviedb.presentation.view.MovieView;

public class MovieFragment extends BaseFragment implements MovieView {

    @InjectPresenter
    MoviePresenter presenter;

    public static MovieFragment newInstance(long movieId) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_movie;
    }
}
