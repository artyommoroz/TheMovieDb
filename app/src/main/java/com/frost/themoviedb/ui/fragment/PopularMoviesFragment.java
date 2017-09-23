package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.network.model.Movie;
import com.frost.themoviedb.presentation.presenter.PopularMoviesPresenter;
import com.frost.themoviedb.presentation.view.PopularMoviesView;
import com.frost.themoviedb.ui.adapter.AdapterClickListener;
import com.frost.themoviedb.ui.adapter.MoviesAdapter;

import java.util.List;

import butterknife.BindView;

public class PopularMoviesFragment extends BaseFragment implements PopularMoviesView, AdapterClickListener<Movie> {

    private static final String BY_GENRES_EXTRA = "by_genres_extra";

    @InjectPresenter
    PopularMoviesPresenter presenter;

    @BindView(R.id.text_view_empty)
    TextView textViewEmpty;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.content_view)
    SwipeRefreshLayout contentView;

    private MoviesAdapter adapter;

    public static PopularMoviesFragment newInstance(String byGenres) {
        PopularMoviesFragment fragment = new PopularMoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString(BY_GENRES_EXTRA, byGenres);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String byGenres = getArguments().getString(BY_GENRES_EXTRA);
            presenter.loadPopularMovies(1, byGenres);
        }
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_popular_movies;
    }

    @Override
    public void setMovies(List<Movie> movies) {
        adapter.setMovies(movies);
    }

    private void initViews() {
        adapter = new MoviesAdapter(getActivity(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position, Movie data) {
        Toast.makeText(getActivity(), "Movie clicked " + data.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
