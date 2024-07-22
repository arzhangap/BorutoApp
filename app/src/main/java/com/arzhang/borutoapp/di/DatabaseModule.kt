package com.arzhang.borutoapp.di

import android.content.Context
import androidx.room.Room
import com.arzhang.borutoapp.data.local.BorutoDatabase
import com.arzhang.borutoapp.util.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        BorutoDatabase::class.java,
        BORUTO_DATABASE
    ).build()

}