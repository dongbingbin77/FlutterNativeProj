package com.androidstudy.androidjetpackdemo.data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.androidstudy.androidjetpackdemo.data.dao.UserDao;
import com.androidstudy.androidjetpackdemo.data.model.User;

@android.arch.persistence.room.Database(
        entities = {User.class},
        version = 1
)

public abstract class Database extends RoomDatabase {

    private static Database INSTANCE;

    public static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), Database.class, "jetpack_db")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();
}
