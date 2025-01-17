package com.arzhang.borutoapp.domain.use_cases

import com.arzhang.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroes
import com.arzhang.borutoapp.domain.use_cases.get_selected_hero.GetSelectedHero
import com.arzhang.borutoapp.domain.use_cases.read_onboarding.ReadOnBoarding
import com.arzhang.borutoapp.domain.use_cases.save_onboarding.SaveOnBoarding
import com.arzhang.borutoapp.domain.use_cases.search_heroes.SearchHeroes

data class UseCases(
    val saveOnBoarding: SaveOnBoarding,
    val readOnBoarding: ReadOnBoarding,
    val getAllHeroes: GetAllHeroes,
    val searchHeroes: SearchHeroes,
    val getSelectedHero: GetSelectedHero
)
