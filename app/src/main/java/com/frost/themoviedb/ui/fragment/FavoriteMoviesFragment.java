package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.network.model.DetailedMovie;
import com.frost.themoviedb.presentation.presenter.FavoriteMoviesPresenter;
import com.frost.themoviedb.presentation.view.FavoriteMoviesView;
import com.frost.themoviedb.ui.adapter.DetailedMoviesAdapter;
import com.frost.themoviedb.ui.adapter.RecyclerClickListener;

import java.util.List;

import butterknife.BindView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class FavoriteMoviesFragment extends BaseFragment implements FavoriteMoviesView, RecyclerClickListener {

    @InjectPresenter
    FavoriteMoviesPresenter presenter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.text_view_empty)
    TextView textViewEmpty;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.content_view)
    SwipeRefreshLayout contentView;

    private DetailedMoviesAdapter adapter;

    public FavoriteMoviesFragment() {
    }

    public static FavoriteMoviesFragment newInstance() {
        FavoriteMoviesFragment fragment = new FavoriteMoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_movies;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadFavoriteMovies();
        initViews();
    }

    private void initViews() {
        contentView.setOnRefreshListener(() -> presenter.loadFavoriteMovies());

        adapter = new DetailedMoviesAdapter(getActivity(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? VISIBLE : GONE);
    }

    @Override
    public void setMovies(List<DetailedMovie> detailedMovies) {
        contentView.setRefreshing(false);
        adapter.setDetailedMovies(detailedMovies);
    }

    @Override
    public void showEmptyView(boolean show) {
        contentView.setRefreshing(false);
        textViewEmpty.setVisibility(show ? VISIBLE : GONE);
    }

    @Override
    public void recyclerItemClicked(int position) {
        presenter.switchToDetailedMovieScreen(adapter.getDetailedMovies().get(position).getId());
    }
}
