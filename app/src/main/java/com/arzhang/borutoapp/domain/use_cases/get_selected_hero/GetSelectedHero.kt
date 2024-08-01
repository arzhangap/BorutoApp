package com.arzhang.borutoapp.domain.use_cases.get_selected_hero

import com.arzhang.borutoapp.data.repository.Repository
import com.arzhang.borutoapp.domain.model.Hero

class GetSelectedHero(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int) : Hero {
       return repository.getSelectedHero(heroId = heroId)
    }
}