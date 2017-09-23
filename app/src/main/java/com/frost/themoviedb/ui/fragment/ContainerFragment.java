package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.network.model.Genre;
import com.frost.themoviedb.presentation.presenter.ContainerPresenter;
import com.frost.themoviedb.presentation.view.ContainerView;
import com.frost.themoviedb.ui.adapter.ContainerPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ContainerFragment extends BaseFragment implements ContainerView {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @InjectPresenter
    ContainerPresenter presenter;

    public static ContainerFragment newInstance() {
//    public static ContainerFragment newInstance(List<Genre> genres) {
        ContainerFragment fragment = new ContainerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_container;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
//        setToolbarTitle(getString(R.string.my_orders));

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(PopularMoviesFragment.newInstance(null));
        fragments.add(FavoriteMoviesFragment.newInstance());
        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.popular_movies_title));
        titles.add(getString(R.string.favorite_movies_title));
        ContainerPagerAdapter pagerAdapter = new ContainerPagerAdapter(getChildFragmentManager(),
                fragments, titles);

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }
}
