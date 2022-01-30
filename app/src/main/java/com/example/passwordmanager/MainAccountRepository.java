package com.example.passwordmanager;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import lombok.NonNull;

public class MainAccountRepository {
    private MainAccountDao mainAccountDao;

    MainAccountRepository(@NonNull Application application){
        MyDatabase database = MyDatabase.getDatabase(application);
        mainAccountDao = database.mainAccountDao();
    }
    MainAccount findMainAccountByLogin(String login){
        return mainAccountDao.findMainAccountByLogin(login);
    }
    void insert(MainAccount account){
        MyDatabase.databaseWriteExecutor.execute(() ->{
            mainAccountDao.insert(account);
        });
    }
    void update(MainAccount account){
        MyDatabase.databaseWriteExecutor.execute(()->{
            mainAccountDao.update(account);
        });
    }
    void delete(MainAccount account){
        MyDatabase.databaseWriteExecutor.execute(()->{
            mainAccountDao.delete(account);
        });
    }
}
