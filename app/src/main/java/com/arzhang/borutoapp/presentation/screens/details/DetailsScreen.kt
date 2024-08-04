package com.arzhang.borutoapp.presentation.screens.details

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collectLatest
import com.arzhang.borutoapp.presentation.screens.details.DetailsScreenViewModel.UiEvent
import com.arzhang.borutoapp.util.Constants.BASE_URL
import com.arzhang.borutoapp.util.PaletteGenerator.convertImageUrlToBitmap
import com.arzhang.borutoapp.util.PaletteGenerator.extractColorsFromBitmap


@Composable
fun DetailScreen(
    navController: NavHostController,
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()
) {
    val selectedHero by detailsScreenViewModel.selectedHero.collectAsState()
    val colorPalette by detailsScreenViewModel.colorPalette

    if(colorPalette.isNotEmpty()) {
        DetailsContent(
            navController = navController,
            selectedHero = selectedHero,
            colors = colorPalette
        )
    } else {
        detailsScreenViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailsScreenViewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.GenerateColorPalette -> {
                    Log.d("uiEvent", "Correct")
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedHero?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        detailsScreenViewModel.setColorPalette(
                            extractColorsFromBitmap(bitmap)
                        )
                    }
                }
            }
        }
    }

}

