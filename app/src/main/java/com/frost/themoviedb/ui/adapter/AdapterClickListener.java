package com.frost.themoviedb.ui.adapter;

public interface AdapterClickListener<T> {

    void onItemClicked(int position, T data);
}
