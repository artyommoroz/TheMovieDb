package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.network.model.Genre;
import com.frost.themoviedb.presentation.presenter.GenresPresenter;
import com.frost.themoviedb.presentation.view.GenresView;
import com.frost.themoviedb.ui.MainActivity;
import com.frost.themoviedb.ui.adapter.AdapterClickListener;
import com.frost.themoviedb.ui.adapter.GenresAdapter;
import com.frost.themoviedb.ui.adapter.RecyclerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class GenresFragment extends BaseFragment implements GenresView, RecyclerClickListener {

    @InjectPresenter
    GenresPresenter presenter;

    //region view binding
    @BindView(R.id.toolbar_genres)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.content_view)
    SwipeRefreshLayout contentView;
    //endregion

    private GenresAdapter adapter;

    public GenresFragment() {
    }

    public static GenresFragment newInstance() {
        GenresFragment fragment = new GenresFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter.loadGenres();
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_genres;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_genres, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            presenter.applyCheckedGenres(getCheckedGenres());
        }
        return false;
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? VISIBLE : GONE);
    }

    @Override
    public void setGenres(List<Genre> genres) {
        adapter.setGenres(genres);
    }

    @Override
    public void onError(String errorMessage) {
        showErrorDialog(errorMessage);
    }

    private void initViews() {
        toolbar.setTitle(getString(R.string.genres_toolbar_title));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> presenter.onBackPressed());

        adapter = new GenresAdapter(getActivity(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private List<Genre> getCheckedGenres() {
        List<Genre> checkedGenres = new ArrayList<>();
        for (Genre genre : adapter.getGenres()) {
            if (genre.isChecked()) {
                checkedGenres.add(genre);
            }
        }
        return checkedGenres;
    }

    @Override
    public void recyclerItemClicked(int position) {
        adapter.getGenres().get(position).setChecked(!adapter.getGenres().get(position).isChecked());
        adapter.notifyItemChanged(position);
    }
}
