package com.devfatani.daggerwithmitch.di

import androidx.lifecycle.ViewModelProvider
import com.devfatani.daggerwithmitch.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModuleKotlin {
    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory?): ViewModelProvider.Factory?
}