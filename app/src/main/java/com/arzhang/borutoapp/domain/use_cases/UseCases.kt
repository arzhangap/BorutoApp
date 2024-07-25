package com.arzhang.borutoapp.domain.use_cases

import com.arzhang.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroes
import com.arzhang.borutoapp.domain.use_cases.read_onboarding.ReadOnBoarding
import com.arzhang.borutoapp.domain.use_cases.save_onboarding.SaveOnBoarding

data class UseCases(
    val saveOnBoarding: SaveOnBoarding,
    val readOnBoarding: ReadOnBoarding,
    val getAllHeroes: GetAllHeroes,
)
