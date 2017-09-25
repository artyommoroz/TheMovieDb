package com.frost.themoviedb.di.module;


import android.app.Application;

import com.frost.themoviedb.database.DatabaseManager;
import com.frost.themoviedb.di.scopes.ApplicationScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class DatabaseModule {

    @Provides
    @ApplicationScope
    Realm provideRealm(Application application) {
        Realm.init(application);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(config);
    }

    @Provides
    @ApplicationScope
    DatabaseManager provideDatabaseManager(Realm realm) {
        return new DatabaseManager(realm);
    }
}
