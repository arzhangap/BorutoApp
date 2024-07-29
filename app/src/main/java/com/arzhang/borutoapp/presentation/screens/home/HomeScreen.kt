package com.arzhang.borutoapp.presentation.screens.home

import android.annotation.SuppressLint
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.arzhang.borutoapp.presentation.common.ListContent
import com.arzhang.borutoapp.presentation.components.AnimatedShimmerItem
import com.arzhang.borutoapp.presentation.components.RatingWidget
import com.arzhang.borutoapp.presentation.components.ShimmerItem
import com.arzhang.borutoapp.ui.theme.LARGE_PADDING

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {})
        },
        content = {
            ListContent(
                heroes = allHeroes,
                navController = navController,
                modifier = Modifier.padding(it)
            )
        }
    )
}