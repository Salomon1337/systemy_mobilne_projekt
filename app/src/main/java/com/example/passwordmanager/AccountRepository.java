package com.example.passwordmanager;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import lombok.NonNull;

public class AccountRepository {
    private AccountDao accountDao;

    AccountRepository(@NonNull Application application){
        MyDatabase database = MyDatabase.getDatabase(application);
        accountDao = database.accountDao();;
    }
    LiveData<List<Account>> findAllAccounts(int mainId){
        return accountDao.findAllAccounts(mainId);
    }
    void insert(Account account){
        MyDatabase.databaseWriteExecutor.execute(() ->{
            accountDao.insert(account);
        });
    }
    void update(Account account){
        MyDatabase.databaseWriteExecutor.execute(()->{
            accountDao.update(account);
        });
    }
    void delete(Account account){
        MyDatabase.databaseWriteExecutor.execute(()->{
            accountDao.delete(account);
        });
    }
    List<Account> findAccountsByName(int mainId, String name){
        return accountDao.findAccountsByName(mainId, name);
    }


}
