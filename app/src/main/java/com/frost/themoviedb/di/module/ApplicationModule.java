package com.frost.themoviedb.di.module;

import android.app.Application;
import android.content.Context;

import com.frost.themoviedb.di.qualifiers.ApplicationContext;

import dagger.Module;
import dagger.Provides;

//@Module
@Module(includes = {ApiModule.class, NavigationModule.class, DatabaseModule.class})
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }
}
