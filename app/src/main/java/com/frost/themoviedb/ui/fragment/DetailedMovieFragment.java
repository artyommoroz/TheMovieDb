package com.frost.themoviedb.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.frost.themoviedb.R;
import com.frost.themoviedb.network.model.DetailedMovie;
import com.frost.themoviedb.presentation.presenter.DetailedMoviePresenter;
import com.frost.themoviedb.presentation.view.DetailedMovieView;

import butterknife.BindView;

public class DetailedMovieFragment extends BaseFragment implements DetailedMovieView {

    private static final String MOVIE_ID_EXTRA = "movie_id_extra";

    @InjectPresenter
    DetailedMoviePresenter presenter;

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

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_detailed_movie;
    }

    @Override
    public void setMovie(DetailedMovie movie) {
        textViewTitle.setText(movie.getTitle());
        textViewOverview.setText(movie.getOverview());
        textViewVoteCount.setText(getString(R.string.detailed_movie_vote_count, movie.getVoteCount()));
        textViewVoteAverage.setText(getString(R.string.detailed_movie_vote_average, movie.getVoteAverage()));
    }
}
