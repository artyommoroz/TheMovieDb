package com.frost.themoviedb.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ContainerView extends MvpView {

    void setToolbarSubtitle(String subtitle);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setGenres(String withGenres);
}
