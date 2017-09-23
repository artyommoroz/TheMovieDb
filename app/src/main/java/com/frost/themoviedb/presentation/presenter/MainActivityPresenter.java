package com.frost.themoviedb.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.frost.themoviedb.App;
import com.frost.themoviedb.presentation.view.MainActivityView;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;

@InjectViewState
public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    @Inject
    NavigatorHolder navigatorHolder;

    public MainActivityPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void setNavigator(Navigator navigator) {
        navigatorHolder.setNavigator(navigator);
    }

    public void removeNavigator() {
        navigatorHolder.removeNavigator();
    }
}
