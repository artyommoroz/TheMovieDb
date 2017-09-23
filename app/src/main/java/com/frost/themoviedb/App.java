package com.frost.themoviedb;


import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    public static App INSTANCE;
//    private static ApplicationComponent appComponent;
    private RealmConfiguration config;

//    public static ApplicationComponent getApplicationComponent() {
//        return appComponent;
//    }

    @Override
    public void onCreate() {
        INSTANCE = this;
        super.onCreate();
//        appComponent = DaggerApplicationComponent
//                .builder()
//                .apiModule(new ApiModule())
//                .applicationModule(new ApplicationModule(this))
//                .ciceroneModule(new CiceroneModule(Cicerone.create()))
//                .dataModule(new DataModule())
//                .build();
//
//        if (BuildConfig.DEBUG) {
//            Stetho.initializeWithDefaults(this);
//        }

        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public Realm getRealmInstance() {
        return Realm.getInstance(config);
    }
}
