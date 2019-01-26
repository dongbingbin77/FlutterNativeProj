package com.androidstudy.androidjetpackdemo.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.androidstudy.androidjetpackdemo.R;
import com.androidstudy.androidjetpackdemo.data.model.User;
import com.androidstudy.androidjetpackdemo.databinding.RowUserBinding;

import java.util.List;

import timber.log.Timber;

public class MainUserAdapter extends RecyclerView.Adapter<MainUserAdapter.ViewHolder> {

    private List<User> userList;
    private Context context;

    public MainUserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public MainUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        RowUserBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.rowUserBinding.setUser(user);
    }

    @Override
    public int getItemCount() {
        Timber.d("Users are : " + userList.size());
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RowUserBinding rowUserBinding;

        ViewHolder(RowUserBinding rowUserBinding) {
            super(rowUserBinding.getRoot());
            this.rowUserBinding = rowUserBinding;
        }
    }
}