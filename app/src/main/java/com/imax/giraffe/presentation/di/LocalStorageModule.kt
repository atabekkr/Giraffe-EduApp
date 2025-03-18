package com.imax.giraffe.presentation.di

import android.content.Context
import com.imax.giraffe.presentation.utils.LocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {
    @Provides
    @Singleton
    fun provideLocalStorage(@ApplicationContext context: Context): LocalStorage {
        val preference = context.getSharedPreferences("SharedPreference", Context.MODE_PRIVATE)
        return LocalStorage(preference)
    }
}