package com.frost.themoviedb.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.frost.themoviedb.R;
import com.frost.themoviedb.network.model.Genre;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {

    private List<Genre> genres = new ArrayList<>();
    private Context context;
//    private AdapterClickListener<Genre> clickListener;
    private RecyclerClickListener clickListener;

//    public GenresAdapter(Context context, AdapterClickListener<Genre> clickListener) {
    public GenresAdapter(Context context, RecyclerClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
        notifyDataSetChanged();
    }

    public List<Genre> getGenres() {
        return genres;
    }

    @Override
    public GenresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        return new GenresAdapter.ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(GenresAdapter.ViewHolder holder, int position) {
        Genre genre = genres.get(position);
        holder.checkBox.setText(genre.getName());
        holder.checkBox.setChecked(genre.isChecked());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox)
        CheckBox checkBox;

        ViewHolder(View view, RecyclerClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            if (clickListener != null) {
                clickListener = listener;
            }
        }

        @OnClick(R.id.checkbox)
        void onCheckBoxClicked() {
            clickListener.recyclerItemClicked(getAdapterPosition());
        }
    }
}
