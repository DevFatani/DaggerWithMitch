package com.devfatani.daggerwithmitch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.devfatani.daggerwithmitch.ui.auth.AuthActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subscribeObservers();
    }

    private void subscribeObservers() {
        sessionManager.getAuthUser().observe(this, authResource -> {
            if (authResource.status != null) {
                switch (authResource.status) {
                    case LOADING:
                        break;
                    case AUTHENTICATED:
                        Log.d(TAG, "subscribeObservers: Login success " + authResource.data.getEmail());
                        break;
                    case NOT_AUTHENTICATED:
                        navLoginScreen();
                        break;
                    case ERROR:
                        break;
                }
            }
        });
    }

    private void navLoginScreen() {
        startActivity(new Intent(this, AuthActivity.class));
        finish();
    }
}
