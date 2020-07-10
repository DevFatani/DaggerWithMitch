package com.devfatani.daggerwithmitch.di.main;

import androidx.lifecycle.ViewModel;

import com.devfatani.daggerwithmitch.di.ViewModelKey;
import com.devfatani.daggerwithmitch.ui.main.posts.PostsViewModel;
import com.devfatani.daggerwithmitch.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindAuthViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostViewModel(PostsViewModel viewModel);
}
