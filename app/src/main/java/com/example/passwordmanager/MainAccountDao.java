package com.example.passwordmanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MainAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (MainAccount mainAccount);

    @Query("SELECT * FROM mainAccount WHERE login = :login")
    public MainAccount findMainAccountByLogin(String login);

    @Update
    public void update (MainAccount mainAccount);

    @Delete
    public void delete (MainAccount mainAccount);

    @Query("DELETE FROM mainAccount WHERE id = :id")
    public void deleteAll(int id);

}
