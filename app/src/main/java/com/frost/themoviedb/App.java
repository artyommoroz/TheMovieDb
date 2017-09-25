package com.frost.themoviedb;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.frost.themoviedb.di.component.ApplicationComponent;
import com.frost.themoviedb.di.component.DaggerApplicationComponent;
import com.frost.themoviedb.di.module.ApiModule;
import com.frost.themoviedb.di.module.ApplicationModule;
import com.frost.themoviedb.di.module.DatabaseModule;
import com.frost.themoviedb.di.module.NavigationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.terrakok.cicerone.Cicerone;

public class App extends Application {

    public static App INSTANCE;
    private static ApplicationComponent appComponent;

    public static ApplicationComponent getApplicationComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        INSTANCE = this;
        super.onCreate();
        appComponent = DaggerApplicationComponent
                .builder()
                .apiModule(new ApiModule())
                .databaseModule(new DatabaseModule())
                .applicationModule(new ApplicationModule(this))
                .navigationModule(new NavigationModule(Cicerone.create()))
                .build();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
