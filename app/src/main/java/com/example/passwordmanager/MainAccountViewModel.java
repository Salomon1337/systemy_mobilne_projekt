package com.example.passwordmanager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import lombok.NonNull;

public class MainAccountViewModel extends AndroidViewModel {
    private MainAccountRepository accountRepository;
    public MainAccountViewModel(@NonNull Application application){
        super(application);
        accountRepository = new MainAccountRepository(application);
    }
    public void insert(MainAccount account){

        accountRepository.insert(account);
    }
    public void update(MainAccount account){

        accountRepository.update(account);

    }
    public void delete(MainAccount account){

        accountRepository.delete(account);
    }
    public MainAccount findMainAccountByLogin(String login){
        return accountRepository.findMainAccountByLogin(login);
    }


}