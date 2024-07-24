package com.arzhang.borutoapp.presentation.screens.home

import android.annotation.SuppressLint
import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {})
        }
    ) {}
}