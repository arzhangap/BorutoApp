package com.arzhang.borutoapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.arzhang.borutoapp.data.repository.Repository
import com.arzhang.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroes(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(query = query)
    }
}