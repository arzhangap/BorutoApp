package com.arzhang.borutoapp.domain.repository

import com.arzhang.borutoapp.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int) : Hero
}