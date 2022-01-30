package com.example.passwordmanager;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName= "mainAccount")
public class MainAccount {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String login;
    private String password;

    public MainAccount(String login, String password){
        this.login=login;
        this.password=password;
    }
    public int getId(){
        return id;
    }
    public String getLogin(){
        return login;
    }
    public String getPassword(){
        return  password;
    }
    public void  setLogin(){
        this.login = login;
    }
    public void setPassword(){
        this.password = password;
    }
    public void setId(int id){
        this.id = id;
    }
}
