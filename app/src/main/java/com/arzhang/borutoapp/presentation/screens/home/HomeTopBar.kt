package com.arzhang.borutoapp.presentation.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arzhang.borutoapp.ui.theme.customColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onSearchClicked: () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.customColorsPalette.topAppbarBackgroundColor ),
        title = {
            Text(text = "Explore", color = MaterialTheme.customColorsPalette.topAppbarContentColor)
        },
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Button", tint = MaterialTheme.customColorsPalette.topAppbarContentColor)
            }
        }
    )
}