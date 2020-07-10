package com.devfatani.daggerwithmitch.di;

import com.devfatani.daggerwithmitch.di.auth.AuthModule;
import com.devfatani.daggerwithmitch.di.auth.AuthScope;
import com.devfatani.daggerwithmitch.di.auth.AuthViewModelsModule;
import com.devfatani.daggerwithmitch.di.main.MainFragmentBuildersModule;
import com.devfatani.daggerwithmitch.di.main.MainModule;
import com.devfatani.daggerwithmitch.di.main.MainScope;
import com.devfatani.daggerwithmitch.di.main.MainViewModelsModule;
import com.devfatani.daggerwithmitch.ui.auth.AuthActivity;
import com.devfatani.daggerwithmitch.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @AuthScope
    @ContributesAndroidInjector(modules = {AuthViewModelsModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(modules = {MainFragmentBuildersModule.class,
            MainViewModelsModule.class, MainModule.class})
    abstract MainActivity contributeMainActivity();
}
