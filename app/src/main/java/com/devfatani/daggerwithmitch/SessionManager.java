package com.devfatani.daggerwithmitch;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.devfatani.daggerwithmitch.models.User;
import com.devfatani.daggerwithmitch.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private static final String TAG = "SessionManager";

    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source) {
        if (cachedUser != null) {
            cachedUser.setValue(AuthResource.loading((User) null));
            cachedUser.addSource(source, authResource -> {
                cachedUser.setValue(authResource);
                cachedUser.removeSource(source);
            });
        }

//        else {
//            Log.d(TAG, "authenticateWithId: previous session detected. Retrieving user from cache");
//        }
    }

    public void logout() {
        Log.d(TAG, "logout: logging out ..");
        cachedUser.setValue(AuthResource.logout());
    }

    public LiveData<AuthResource<User>> getAuthUser() {
        return cachedUser;
    }
}
