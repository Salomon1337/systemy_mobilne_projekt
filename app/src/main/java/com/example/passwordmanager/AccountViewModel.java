package com.example.passwordmanager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import lombok.NonNull;

public class AccountViewModel extends AndroidViewModel {
    private AccountRepository accountRepository;

    public AccountViewModel(@NonNull Application application){
        super(application);
        accountRepository = new AccountRepository(application);
    }
    public LiveData<List<Account>> findAllAccounts(int mainId){
        return accountRepository.findAllAccounts(mainId);
    }
    public void insert(Account account){

            accountRepository.insert(account);
    }
    public void update(Account account){

            accountRepository.update(account);

    }
    public void delete(Account account){

            accountRepository.delete(account);

    }
    public List<Account> findAccountsByName(int mainId, String name){
        return accountRepository.findAccountsByName(mainId, name);
    }



}
