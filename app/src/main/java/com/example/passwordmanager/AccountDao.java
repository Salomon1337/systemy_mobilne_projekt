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
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Account account);

    @Update
    public void update(Account account);

    @Delete
    public void delete(Account account);

    @Query("DELETE FROM account")
    public void deleteAll();

    @Query("DELETE FROM account WHERE id = :id")
    public void deleteId(int id);

    @Query("SELECT * FROM account WHERE mainId = :mainId ORDER BY name")
    public LiveData<List<Account>> findAllAccounts(int mainId);

    @Query("SELECT * FROM account WHERE mainId = :mainId AND name LIKE '%' || :name || '%' ORDER BY name")
    public List<Account> findAccountsByName(int mainId, String name);
}
