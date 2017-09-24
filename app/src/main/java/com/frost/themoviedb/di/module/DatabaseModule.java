package com.frost.themoviedb.di.module;


import android.app.Application;

import com.frost.themoviedb.database.DatabaseManager;

import javax.inject.Singleton;

import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DatabaseModule {

    @Provides
    @Singleton
    Realm provideRealm(Application application) {
        Realm.init(application);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(config);
    }

    @Provides
    @Singleton
    DatabaseManager provideDbManager(Realm realm) {
        return new DatabaseManager(realm);
    }
}
