package com.frost.themoviedb.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.Screens;
import com.frost.themoviedb.presentation.presenter.MainActivityPresenter;
import com.frost.themoviedb.presentation.view.MainActivityView;
import com.frost.themoviedb.ui.fragment.ContainerFragment;
import com.frost.themoviedb.ui.fragment.FavoriteMoviesFragment;
import com.frost.themoviedb.ui.fragment.GenresFragment;
import com.frost.themoviedb.ui.fragment.MovieFragment;
import com.frost.themoviedb.ui.fragment.PopularMoviesFragment;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView {

//    @Inject
//    NavigatorHolder navigatorHolder;

    @InjectPresenter
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        ActivityComponent activityComponent =
//                App.getApplicationComponent().providesActivityComponent(new ActivityModule(this));
//        activityComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        navigator.applyCommand(new Replace(Screens.CONTAINER_SCREEN, null));
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
                    return ContainerFragment.newInstance();
//                case Screens.SEARCH_MOVIES_SCREEN:
//                    return ProfileFragment.newInstance();
                case Screens.MOVIE_SCREEN:
                    return MovieFragment.newInstance(21);
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
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
            super.setupFragmentTransactionAnimation(command, currentFragment, nextFragment, fragmentTransaction);
        }
    };
}
