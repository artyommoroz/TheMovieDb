package com.frost.themoviedb.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.Screens;
import com.frost.themoviedb.network.model.DetailedMovieContainer;
import com.frost.themoviedb.network.model.Genre;
import com.frost.themoviedb.presentation.presenter.MainActivityPresenter;
import com.frost.themoviedb.presentation.view.MainActivityView;
import com.frost.themoviedb.ui.fragment.ContainerFragment;
import com.frost.themoviedb.ui.fragment.GenresFragment;
import com.frost.themoviedb.ui.fragment.DetailedMovieFragment;
import com.frost.themoviedb.ui.fragment.SearchMoviesFragment;

import java.util.ArrayList;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView {

    @InjectPresenter
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigator.applyCommand(new Replace(Screens.CONTAINER_SCREEN, null));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        presenter.removeNavigator();
        super.onPause();
    }

    protected Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(),
            R.id.layout_container) {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.CONTAINER_SCREEN:
                    return ContainerFragment.newInstance((ArrayList<Genre>) data);
                case Screens.SEARCH_MOVIES_SCREEN:
                    return SearchMoviesFragment.newInstance();
                case Screens.MOVIE_SCREEN:
                    return DetailedMovieFragment.newInstance((DetailedMovieContainer) data);
                case Screens.GENRES_SCREEN:
                    return GenresFragment.newInstance();
            }
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {

        }

        @Override
        protected void exit() {
            finish();
        }

        @Override
        protected void setupFragmentTransactionAnimation(Command command, Fragment currentFragment,
                                                         Fragment nextFragment,
                                                         FragmentTransaction fragmentTransaction) {
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out,
                    R.anim.fade_in, R.anim.fade_out);
            super.setupFragmentTransactionAnimation(command, currentFragment, nextFragment, fragmentTransaction);
        }
    };
}
