package com.arzhang.borutoapp.di

import android.content.Context
import com.arzhang.borutoapp.data.pref.DataStoreOperationImpl
import com.arzhang.borutoapp.domain.repository.DataStoreOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.contracts.Returns

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ) : DataStoreOperations {
        return DataStoreOperationImpl(context = context)
    }
}
