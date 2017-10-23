package com.dream.lemon.hackathon.ui.welcome;

import android.support.v4.app.Fragment;

public class WelcomeFragment extends Fragment implements WelcomeContract.View {

    private WelcomeContract.Presenter presenter;

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Override
    public void setPresenter(WelcomeContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        } else {
            throw new NullPointerException();
        }
    }
}
