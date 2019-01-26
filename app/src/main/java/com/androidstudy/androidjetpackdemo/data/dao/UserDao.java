package com.androidstudy.androidjetpackdemo.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.androidstudy.androidjetpackdemo.data.model.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    //Insert User
    @Insert(onConflict = REPLACE)
    void addUser(User user);

    //Get All Users
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();
}
