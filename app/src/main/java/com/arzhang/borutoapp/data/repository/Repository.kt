package com.arzhang.borutoapp.data.repository

import androidx.paging.PagingData
import com.arzhang.borutoapp.domain.model.Hero
import com.arzhang.borutoapp.domain.repository.DataStoreOperations
import com.arzhang.borutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations,
    private val remote: RemoteDataSource
 ){

    // REMOTE DATA SOURCE
    fun getAllHeroes() : Flow<PagingData<Hero>> {
        return remote.getAllHeroes()
    }

    fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return remote.searchHeroes(query = query)
    }

    //DATA SOURCE
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState() : Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

}