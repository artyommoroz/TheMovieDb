package com.frost.themoviedb.di.module;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class NavigationModule {

    Cicerone<Router> cicerone;

    public NavigationModule(Cicerone<Router> cicerone) {
        this.cicerone = cicerone;
    }

    @Provides
    Router provideRouter() {
        return cicerone.getRouter();
    }

    @Provides
    NavigatorHolder provideNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }
}
