package com.arzhang.borutoapp.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.arzhang.borutoapp.presentation.common.ListContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController:NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(
                paddingValue = PaddingValues(top = 30.dp),
                text = searchQuery,
                onTextChange = { query ->
                    searchViewModel.updateSearchQuery(query)
                },
                onSearchClicked = { query ->
                    searchViewModel.searchHeroes(query = query)
                },
                onClosedClicked = {
                    navController.popBackStack()
                }
            )
        }
    ){
        ListContent(
            heroes = heroes,
            navController = navController,
            modifier = Modifier.padding(it)
        )
    }
}