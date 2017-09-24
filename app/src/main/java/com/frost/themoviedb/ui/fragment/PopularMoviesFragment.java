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
import com.frost.themoviedb.network.model.Movie;
import com.frost.themoviedb.presentation.presenter.PopularMoviesPresenter;
import com.frost.themoviedb.presentation.view.PopularMoviesView;
import com.frost.themoviedb.ui.adapter.AdapterClickListener;
import com.frost.themoviedb.ui.adapter.MoviesAdapter;

import java.util.List;

import butterknife.BindView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.frost.themoviedb.ui.adapter.MoviesAdapter.MOVIE_TYPE_POPULAR;

public class PopularMoviesFragment extends BaseFragment implements PopularMoviesView,
        AdapterClickListener<Movie> {

    private static final String WITH_GENRES_EXTRA = "with_genres_extra";

    @InjectPresenter
    PopularMoviesPresenter presenter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.text_view_empty)
    TextView textViewEmpty;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.content_view)
    SwipeRefreshLayout contentView;

    private MoviesAdapter adapter;
    private String withGenres;

    public static PopularMoviesFragment newInstance(String withGenres) {
        PopularMoviesFragment fragment = new PopularMoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString(WITH_GENRES_EXTRA, withGenres);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            withGenres = getArguments().getString(WITH_GENRES_EXTRA);
            presenter.loadPopularMovies(1, withGenres);
        }
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_movies;
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? VISIBLE : GONE);
    }

    @Override
    public void setMovies(List<Movie> movies) {
        contentView.setRefreshing(false);
        adapter.setMovies(movies);
    }

    @Override
    public void showEmptyView(boolean show) {
        contentView.setRefreshing(false);
        textViewEmpty.setVisibility(show ? VISIBLE : GONE);
    }

    private void initViews() {
        contentView.setOnRefreshListener(() -> {
            presenter.loadPopularMovies(1, withGenres);
        });

        adapter = new MoviesAdapter(getActivity(), MOVIE_TYPE_POPULAR, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position, Movie data) {
        presenter.switchToDetailedMovieScreen(data.getId());
    }
}
