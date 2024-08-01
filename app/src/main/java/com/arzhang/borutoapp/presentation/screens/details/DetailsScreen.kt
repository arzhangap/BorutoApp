package com.arzhang.borutoapp.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(
    navController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val selectedHero by detailsScreenViewModel.selectedHero.collectAsState()

    DetailsContent(
        navController = navController,
        selectedHero = selectedHero
    )
}
