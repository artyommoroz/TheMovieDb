package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.common.RxSearch;
import com.frost.themoviedb.network.model.Movie;
import com.frost.themoviedb.presentation.presenter.SearchMoviesPresenter;
import com.frost.themoviedb.presentation.view.MoviesView;
import com.frost.themoviedb.ui.MainActivity;
import com.frost.themoviedb.ui.adapter.AdapterClickListener;
import com.frost.themoviedb.ui.adapter.MoviesAdapter;
import com.frost.themoviedb.ui.adapter.RecyclerClickListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.frost.themoviedb.ui.adapter.MoviesAdapter.MOVIE_TYPE_SEARCH;

public class SearchMoviesFragment extends BaseFragment implements MoviesView, RecyclerClickListener{

    private static final long INTERVAL_MILLISECONDS = 300;

    @InjectPresenter
    SearchMoviesPresenter presenter;

    //region view binding
    @BindView(R.id.toolbar_search)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.text_view_empty)
    TextView textViewEmpty;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.content_view)
    SwipeRefreshLayout contentView;
    //endregion

    private String query;
    private MoviesAdapter adapter;
    private Disposable searchDisposable;

    public SearchMoviesFragment() {
    }

    public static SearchMoviesFragment newInstance() {
        SearchMoviesFragment fragment = new SearchMoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    public void onDestroy() {
        if (searchDisposable != null) {
            searchDisposable.dispose();
        }
        super.onDestroy();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_search_movies;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchMenuItem.expandActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQuery(query, false);
        searchDisposable = RxSearch.fromSearchView(searchView)
                .debounce(INTERVAL_MILLISECONDS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> {
                    this.query = query;
                    presenter.loadMovies(query);
                });
        MenuItemCompat.setOnActionExpandListener(searchMenuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                presenter.onBackPressed();
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? VISIBLE : GONE);
    }

    @Override
    public void setMovies(List<Movie> movies) {
        adapter.setMovies(movies);
    }

    @Override
    public void showEmptyView(boolean show) {
        textViewEmpty.setVisibility(show ? VISIBLE : GONE);
    }

    @Override
    public void onError(String errorMessage) {
        showErrorDialog(errorMessage);
    }

    private void initViews() {
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        adapter = new MoviesAdapter(getActivity(), MOVIE_TYPE_SEARCH, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void recyclerItemClicked(int position) {
        presenter.switchToDetailedMovieScreen(adapter.getMovies().get(position).getId());
    }
}
