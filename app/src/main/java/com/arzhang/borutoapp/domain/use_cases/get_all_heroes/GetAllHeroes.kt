package com.arzhang.borutoapp.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.arzhang.borutoapp.data.repository.Repository
import com.arzhang.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroes(
    private val repository: Repository
) {
    operator fun invoke() : Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }
}