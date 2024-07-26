package com.arzhang.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arzhang.borutoapp.data.local.BorutoDatabase
import com.arzhang.borutoapp.data.local.dao.HeroDao
import com.arzhang.borutoapp.data.paging_source.HeroRemoteMediator
import com.arzhang.borutoapp.data.remote.BorutoApi
import com.arzhang.borutoapp.domain.model.Hero
import com.arzhang.borutoapp.domain.repository.RemoteDataSource
import com.arzhang.borutoapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

// Paging Source: retrieve data from source. (in here is local database)
//
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteDataSource {

    private val heroDao = borutoDatabase.heroDao()

    @OptIn(ExperimentalPagingApi::class)
    // Returns DATA IN FLOW
    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        // set the single source of truth
        val pagingSourceFactory = {heroDao.getAllHeroes()}
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(borutoApi, borutoDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }

}