package com.example.passwordmanager;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomPasswordService {
    @GET("/query?command=password&format=json&count=1")
    Call<PasswordWrapper> getPasswords();
}

