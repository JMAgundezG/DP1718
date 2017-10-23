package com.dream.lemon.hackathon.ui.welcome;

/**
 * Created by ninja on 23/10/2017.
 */

public class WelcomePresenter implements WelcomeContract.Presenter {

    private final WelcomeContract.View view;

    public WelcomePresenter(WelcomeContract.View view) {
        if (view != null) {
            this.view = view;
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public void start() {
        view.setPresenter(this);
    }
}
