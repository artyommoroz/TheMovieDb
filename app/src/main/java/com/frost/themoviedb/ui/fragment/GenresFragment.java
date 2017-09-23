package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.presentation.presenter.GenresPresenter;
import com.frost.themoviedb.presentation.view.GenresView;

public class GenresFragment extends BaseFragment implements GenresView {

    @InjectPresenter
    GenresPresenter presenter;

    public static GenresFragment newInstance() {
        GenresFragment fragment = new GenresFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_genres;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
