package com.frost.themoviedb.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frost.themoviedb.R;
import com.frost.themoviedb.network.model.DetailedMovie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.VISIBLE;
import static com.frost.themoviedb.common.Constants.IMAGE_PATH;

public class DetailedMoviesAdapter extends RecyclerView.Adapter<DetailedMoviesAdapter.ViewHolder> {

    private List<DetailedMovie> detailedMovies = new ArrayList<>();
    private Context context;
    private RecyclerClickListener clickListener;

    public DetailedMoviesAdapter(Context context, RecyclerClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setDetailedMovies(List<DetailedMovie> detailedMovies) {
        this.detailedMovies = detailedMovies;
        notifyDataSetChanged();
    }

    public List<DetailedMovie> getDetailedMovies() {
        return detailedMovies;
    }

    @Override
    public DetailedMoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new DetailedMoviesAdapter.ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(DetailedMoviesAdapter.ViewHolder holder, int position) {
        DetailedMovie movie = detailedMovies.get(position);
        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewOverview.setText(movie.getOverview());

        holder.textViewOverview.setVisibility(VISIBLE);

        if (!TextUtils.isEmpty(movie.getPosterPath())) {
            Glide.with(context)
                    .load(IMAGE_PATH + movie.getPosterPath())
                    .into(holder.imageViewPoster);
        }
    }

    @Override
    public int getItemCount() {
        return detailedMovies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.root)
        CardView root;
        @BindView(R.id.image_view_poster)
        ImageView imageViewPoster;
        @BindView(R.id.text_view_title)
        TextView textViewTitle;
        @BindView(R.id.text_view_overview)
        TextView textViewOverview;

        ViewHolder(View view, RecyclerClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            if (clickListener != null) {
                clickListener = listener;
            }
        }

        @OnClick(R.id.root)
        void onRootClicked() {
            clickListener.recyclerItemClicked(getAdapterPosition());
        }
    }
}
