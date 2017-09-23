package com.frost.themoviedb.di.component;


import com.frost.themoviedb.di.module.ActivityModule;
import com.frost.themoviedb.di.scopes.ActivityScope;
import com.frost.themoviedb.ui.MainActivity;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
}
