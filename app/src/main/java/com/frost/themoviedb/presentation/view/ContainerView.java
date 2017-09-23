package com.frost.themoviedb.presentation.view;

import com.arellomobile.mvp.MvpView;

public interface ContainerView extends MvpView {

    void setToolbarSubtitle(String subtitle);

    void setQuery(String query);
}
