package com.arzhang.borutoapp.data.repository

import com.arzhang.borutoapp.data.local.BorutoDatabase
import com.arzhang.borutoapp.domain.model.Hero
import com.arzhang.borutoapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(borutoDatabase: BorutoDatabase) : LocalDataSource{
    private val heroDao = borutoDatabase.heroDao()
    override suspend fun getSelectedHero(heroId: Int): Hero =
        heroDao.getSelectedHero(heroId
        )
}