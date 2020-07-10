package com.devfatani.daggerwithmitch.di.auth;

import androidx.lifecycle.ViewModel;

import com.devfatani.daggerwithmitch.di.ViewModelKey;
import com.devfatani.daggerwithmitch.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
