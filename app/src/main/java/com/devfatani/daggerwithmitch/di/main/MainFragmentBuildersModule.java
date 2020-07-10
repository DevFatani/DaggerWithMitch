package com.devfatani.daggerwithmitch.di.main;

import com.devfatani.daggerwithmitch.ui.main.posts.PostsFragment;
import com.devfatani.daggerwithmitch.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector(modules = {})
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector(modules = {})
    abstract PostsFragment contributePostsFragment();
}
