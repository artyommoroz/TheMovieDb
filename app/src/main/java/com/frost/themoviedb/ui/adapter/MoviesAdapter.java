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

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.frost.themoviedb.common.Constants.IMAGE_PATH;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    public static final int MOVIE_TYPE_POPULAR = 1;
    public static final int MOVIE_TYPE_FAVORITE = 2;
    public static final int MOVIE_TYPE_SEARCH = 3;

    private List<Movie> movies = new ArrayList<>();
    private Context context;
    private int movieType;
    private AdapterClickListener<Movie> clickListener;

    public MoviesAdapter(Context context, int movieType, AdapterClickListener<Movie> clickListener) {
        this.context = context;
        this.movieType = movieType;
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
        Movie movie = movies.get(position);
        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewOverview.setText(movie.getOverview());
        holder.textViewReleaseDate.setText(String.valueOf(movie.getReleaseDate()));

        holder.textViewOverview.setVisibility(movieType != MOVIE_TYPE_SEARCH ? VISIBLE : GONE);
        holder.textViewReleaseDate.setVisibility(movieType == MOVIE_TYPE_SEARCH ? VISIBLE : GONE);

        if (!TextUtils.isEmpty(movie.getPosterPath())) {
            Glide.with(context)
                    .load(IMAGE_PATH + movie.getPosterPath())
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
        @BindView(R.id.text_view_release_date)
        TextView textViewReleaseDate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
