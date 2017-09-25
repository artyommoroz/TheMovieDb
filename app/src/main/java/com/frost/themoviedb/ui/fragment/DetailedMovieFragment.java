package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.frost.themoviedb.R;
import com.frost.themoviedb.network.model.DetailedMovie;
import com.frost.themoviedb.network.model.DetailedMovieContainer;
import com.frost.themoviedb.network.model.Genre;
import com.frost.themoviedb.presentation.presenter.DetailedMoviePresenter;
import com.frost.themoviedb.presentation.view.DetailedMovieView;
import com.frost.themoviedb.ui.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.frost.themoviedb.common.Constants.IMAGE_PATH;

public class DetailedMovieFragment extends BaseFragment implements DetailedMovieView {

    private static final String MOVIE_ID_EXTRA = "movie_id_extra";
    private static final String IS_FAVORITE_EXTRA = "is_favorite_extra";

    @InjectPresenter
    DetailedMoviePresenter presenter;

    //region view binding
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
    @BindView(R.id.button_add_to_favorites)
    Button buttonAddToFavorites;
    @BindView(R.id.button_delete_from_favorites)
    Button buttonDeleteFromFavorites;
    //endregion

    private DetailedMovie movie;

    public DetailedMovieFragment() {
    }

    public static DetailedMovieFragment newInstance(DetailedMovieContainer container) {
        DetailedMovieFragment fragment = new DetailedMovieFragment();
        Bundle args = new Bundle();
        args.putLong(MOVIE_ID_EXTRA, container.getMovieId());
        args.putBoolean(IS_FAVORITE_EXTRA, container.isFavorite());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            long movieId = getArguments().getLong(MOVIE_ID_EXTRA);
            boolean isFavorite = getArguments().getBoolean(IS_FAVORITE_EXTRA);
            presenter.loadMovie(movieId, isFavorite);
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
        if (movie.isFavorite()) {
            buttonAddToFavorites.setEnabled(false);
            buttonDeleteFromFavorites.setEnabled(true);
        }
        if (movie.getVoteCount() != 0) {
            textViewVoteCount.setText(getString(R.string.detailed_movie_vote_count, movie.getVoteCount()));
        }
        if (movie.getVoteAverage() != 0) {
            textViewVoteAverage.setText(getString(R.string.detailed_movie_vote_average, movie.getVoteAverage()));
        }
        if (movie.getGenres() != null) {
            StringBuilder genres = new StringBuilder();
            for (Genre genre : movie.getGenres()) {
                genres.append(genre.getName()).append(" ");
            }
            textViewGenres.setText(getString(R.string.detailed_movie_genres, genres.toString()));
        }
        if (!TextUtils.isEmpty(movie.getPosterPath())) {
            Glide.with(getActivity())
                    .load(IMAGE_PATH + movie.getPosterPath())
                    .into(imageViewPoster);
        }
    }

    @Override
    public void onMovieAddedToFavorites() {
        buttonAddToFavorites.setEnabled(false);
        buttonDeleteFromFavorites.setEnabled(true);
        showToastMessage(R.string.detailed_movie_toast_add_to_favorites);
    }

    @Override
    public void onMovieDeletedFromFavorites() {
        buttonAddToFavorites.setEnabled(true);
        buttonDeleteFromFavorites.setEnabled(false);
        showToastMessage(R.string.detailed_movie_toast_delete_from_favorites);
    }

    @Override
    public void onError(String errorMessage) {
        showErrorDialog(errorMessage);
    }

    @OnClick(R.id.button_add_to_favorites)
    public void onAddToFavoritesClicked() {
        if (movie != null) {
            presenter.addToFavorites(movie);
        }
    }

    @OnClick(R.id.button_delete_from_favorites)
    public void onDeleteFromFavoritesClicked() {
        if (movie != null) {
            presenter.deleteFromFavorites(movie.getId());
        }
    }

    private void initViews() {
        toolbar.setTitle(getString(R.string.detailed_movie_toolbar_title));
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> presenter.onBackPressed());
    }
}
