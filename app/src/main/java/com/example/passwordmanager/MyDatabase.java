package com.example.passwordmanager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = { Account.class, MainAccount.class }, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    private static volatile MyDatabase instance;

    public abstract AccountDao accountDao();
    public abstract MainAccountDao mainAccountDao();

    public static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyDatabase getDatabase(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "MyDatabase")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
