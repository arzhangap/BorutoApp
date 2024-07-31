package com.arzhang.borutoapp.di

import android.content.Context
import com.arzhang.borutoapp.data.repository.DataStoreOperationImpl
import com.arzhang.borutoapp.data.repository.Repository
import com.arzhang.borutoapp.domain.repository.DataStoreOperations
import com.arzhang.borutoapp.domain.use_cases.UseCases
import com.arzhang.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroes
import com.arzhang.borutoapp.domain.use_cases.read_onboarding.ReadOnBoarding
import com.arzhang.borutoapp.domain.use_cases.save_onboarding.SaveOnBoarding
import com.arzhang.borutoapp.domain.use_cases.search_heroes.SearchHeroes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoarding = SaveOnBoarding(repository),
            readOnBoarding = ReadOnBoarding(repository),
            getAllHeroes = GetAllHeroes(repository),
            searchHeroes = SearchHeroes(repository)
        )
    }
}

