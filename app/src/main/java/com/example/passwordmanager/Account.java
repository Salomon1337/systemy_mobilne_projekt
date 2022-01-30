package com.example.passwordmanager;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "account",foreignKeys = {@ForeignKey(entity = MainAccount.class,
        parentColumns = "id",
        childColumns = "mainId",
        onDelete = ForeignKey.CASCADE)
})
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int mainId;
    private String name;
    private String login;
    private String password;
    private String email;
    private String uri;

    public Account(String login, String password, String email, String uri, String name, int mainId){
        this.login = login;
        this.password = password;
        this.email = email;
        this.uri = uri;
        this.name = name;
        this.mainId = mainId;
    }
    public  String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getId(){
        return id;
    }
    public int getMainId(){
        return mainId;
    }
    public void setMainId(int mainId){
        this.mainId = mainId;
    }
    public String getLogin(){
        return login;
    }
    public String getPassword(){
        return  password;
    }
    public void  setLogin(String login){
        this.login = login;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getEmail(){
        return email;
    }
    public String getUri(){
        return uri;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setUri(String uri){
        this.uri = uri;
    }
}