package com.devfatani.daggerwithmitch.di;

import androidx.lifecycle.ViewModelProvider;

import com.devfatani.daggerwithmitch.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory
    bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
