package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.network.model.Genre;
import com.frost.themoviedb.presentation.presenter.ContainerPresenter;
import com.frost.themoviedb.presentation.view.ContainerView;
import com.frost.themoviedb.ui.MainActivity;
import com.frost.themoviedb.ui.adapter.ContainerPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ContainerFragment extends BaseFragment implements ContainerView {

    private static final String GENRES_EXTRA = "movie_id_extra";

    @InjectPresenter
    ContainerPresenter presenter;

    @BindView(R.id.toolbar_container)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private List<Genre> genres;

    public static ContainerFragment newInstance(ArrayList<Genre> genres) {
        ContainerFragment fragment = new ContainerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(GENRES_EXTRA, genres);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            genres = getArguments().getParcelableArrayList(GENRES_EXTRA);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_container;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_container, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            presenter.switchToSearchMoviesScreen();
        } else if (item.getItemId() == R.id.action_filter) {
            presenter.switchToGenresScreen();
        }
        return false;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        presenter.convertGenreNamesToString(genres);
        presenter.convertGenreIdsToString(genres);
    }

    @Override
    public void setToolbarSubtitle(String subtitle) {
        toolbar.setSubtitle(subtitle);
    }

    @Override
    public void setGenres(String withGenres) {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(PopularMoviesFragment.newInstance(withGenres));
        fragments.add(FavoriteMoviesFragment.newInstance());
        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.popular_movies_toolbar_title));
        titles.add(getString(R.string.favorite_movies_toolbar_title));
        ContainerPagerAdapter pagerAdapter = new ContainerPagerAdapter(getChildFragmentManager(),
                fragments, titles);

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private void initViews() {
        toolbar.setTitle("TMbd");
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
    }
}
