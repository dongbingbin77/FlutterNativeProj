package com.androidstudy.androidjetpackdemo.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.androidstudy.androidjetpackdemo.data.Database;
import com.androidstudy.androidjetpackdemo.data.model.User;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Database appDatabase;
    private final LiveData<List<User>> userList;

    public MainViewModel(@NonNull Application application) {
        super(application);
        appDatabase = Database.getDatabase(this.getApplication());
        //Initialize User List
        userList = appDatabase.userDao().getAllUsers();
    }

    //Add New User
    public void addUser(User user) {
        new addAsyncTask(appDatabase).execute(user);
    }

    // Get Users
    public LiveData<List<User>> getUserList() {
        return userList;
    }

    private static class addAsyncTask extends AsyncTask<User, Void, Void> {

        private Database db;

        addAsyncTask(Database appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final User... params) {
            db.userDao().addUser(params[0]);
            return null;
        }

    }
}
