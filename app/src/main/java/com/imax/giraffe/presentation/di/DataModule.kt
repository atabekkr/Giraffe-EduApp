package com.imax.giraffe.presentation.di

import android.content.Context
import androidx.room.Room
import com.imax.giraffe.presentation.data.db.GiraffeDao
import com.imax.giraffe.presentation.data.db.GiraffeDatabase
import com.imax.giraffe.presentation.data.db.GradeCompletionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): GiraffeDatabase {
        return Room.databaseBuilder(context, GiraffeDatabase::class.java, "Giraffe_new_db_v2.db")
            .createFromAsset("Giraffe_new_db_v2.db")
//            .fallbackToDestructiveMigration() this code resets the db after relaunching the app.
            .build()
    }

    @Provides
    @Singleton
    fun provideLocationDao(database: GiraffeDatabase): GiraffeDao {
        return database.getLocationDao()
    }

    @Provides
    @Singleton
    fun provideGradeCompletionDao(database: GiraffeDatabase): GradeCompletionDao {
        return database.getGradeCompletionDao()
    }

}