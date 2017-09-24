package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.frost.themoviedb.R;
import com.frost.themoviedb.network.model.DetailedMovie;
import com.frost.themoviedb.presentation.presenter.DetailedMoviePresenter;
import com.frost.themoviedb.presentation.view.DetailedMovieView;
import com.frost.themoviedb.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.frost.themoviedb.common.Constants.IMAGE_PATH;

public class DetailedMovieFragment extends BaseFragment implements DetailedMovieView {

    private static final String MOVIE_ID_EXTRA = "movie_id_extra";

    @InjectPresenter
    DetailedMoviePresenter presenter;

    @BindView(R.id.toolbar_detailed_movie)
    Toolbar toolbar;
    @BindView(R.id.image_view_poster)
    ImageView imageViewPoster;
    @BindView(R.id.text_view_title)
    TextView textViewTitle;
    @BindView(R.id.text_view_overview)
    TextView textViewOverview;
    @BindView(R.id.text_view_date)
    TextView textViewDate;
    @BindView(R.id.text_view_vote_count)
    TextView textViewVoteCount;
    @BindView(R.id.text_view_vote_average)
    TextView textViewVoteAverage;
    @BindView(R.id.text_view_genres)
    TextView textViewGenres;

    private DetailedMovie movie;

    public DetailedMovieFragment() {
    }

    public static DetailedMovieFragment newInstance(long movieId) {
        DetailedMovieFragment fragment = new DetailedMovieFragment();
        Bundle args = new Bundle();
        args.putLong(MOVIE_ID_EXTRA, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            long movieId = getArguments().getLong(MOVIE_ID_EXTRA);
            presenter.loadMovie(movieId);
        }
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_detailed_movie;
    }

    @Override
    public void setMovie(DetailedMovie movie) {
        this.movie = movie;
        textViewTitle.setText(movie.getTitle());
        textViewOverview.setText(movie.getOverview());
        if (movie.getVoteCount() != 0) {
            textViewVoteCount.setText(getString(R.string.detailed_movie_vote_count, movie.getVoteCount()));
        }
        if (movie.getVoteAverage() != 0) {
            textViewVoteAverage.setText(getString(R.string.detailed_movie_vote_average, movie.getVoteAverage()));
        }
        if (!TextUtils.isEmpty(movie.getPosterPath())) {
            Glide.with(getActivity())
                    .load(IMAGE_PATH + movie.getPosterPath())
                    .into(imageViewPoster);
        }
    }

    @Override
    public void onMovieAddedToFavorites() {
        showToastMessage(R.string.detailed_movie_toast_add_to_favorites);
    }

    @Override
    public void onMovieDeletedFromFavorites() {
        showToastMessage(R.string.detailed_movie_toast_delete_from_favorites);
    }

    @OnClick(R.id.button_add_to_favorites)
    public void onAddToFavoritesClicked() {
        presenter.addToFavorites(movie);
    }

    @OnClick(R.id.button_delete_from_favorites)
    public void onDeleteFromFavoritesClicked() {
        presenter.deleteFromFavorites(movie.getId());
    }

    private void initViews() {
        toolbar.setTitle(getString(R.string.detailed_movie_toolbar_title));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
