package com.devfatani.daggerwithmitch.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.devfatani.daggerwithmitch.SessionManager;
import com.devfatani.daggerwithmitch.models.User;
import com.devfatani.daggerwithmitch.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;


public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    private SessionManager sessionManager;

    @Inject
    public AuthViewModel( AuthApi authApi, SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
    }

    public void authenticateWithId(int userId) {
        Log.d(TAG, "authenticateWithId: attempting to login");
        sessionManager.authenticateWithId(queryUserId(userId));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId) {
        return LiveDataReactiveStreams
                        .fromPublisher(authApi.getUser(userId)
                                .onErrorReturn(throwable -> {
                                    User errorUser = new User();
                                    errorUser.setId(-1);
                                    return errorUser;
                                })
                                .map(user -> {
                                    if (user.getId() == -1) {
                                        return AuthResource.error("Could not authenticate", (User) null);
                                    }
                                    return AuthResource.authenticated(user);
                                })
                                .subscribeOn(Schedulers.io()));
    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getAuthUser();
    }
}
