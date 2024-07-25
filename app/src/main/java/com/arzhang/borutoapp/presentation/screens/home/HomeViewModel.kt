package com.arzhang.borutoapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.arzhang.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel(
    useCases: UseCases
) : ViewModel() {

    val getAllHeroes = useCases.getAllHeroes()

}