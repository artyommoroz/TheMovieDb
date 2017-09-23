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
import com.frost.themoviedb.network.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.frost.themoviedb.common.Constants.IMAGE_PATH;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private Context context;
    private AdapterClickListener<Movie> clickListener;

    public MoviesAdapter(Context context, AdapterClickListener<Movie> clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MoviesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        Movie movie = movies.get(position);
        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewOverview.setText(movie.getOverview());

        if (!TextUtils.isEmpty(movie.getPosterPath())) {
            Glide.with(context)
                    .load(IMAGE_PATH + movie.getPosterPath())
//                    .transform(new RoundedCornersTransformation(5, 0))
                    .into(holder.imageViewPoster);
        }
        holder.root.setOnClickListener(view -> {
            clickListener.onItemClicked(position, movie);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
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

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
