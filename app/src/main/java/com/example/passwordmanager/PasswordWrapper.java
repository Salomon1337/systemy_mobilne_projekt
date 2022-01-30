package com.example.passwordmanager;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PasswordWrapper {
    @SerializedName("char")
    private List<String> passwords;

    public List<String> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<String> passwords) {
        this.passwords = passwords;
    }
}
