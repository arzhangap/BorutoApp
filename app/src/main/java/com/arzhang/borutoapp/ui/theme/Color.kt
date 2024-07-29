package com.arzhang.borutoapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Purple700 = Color(0xFF3700B3)
val Purple500 = Color(0xFF6200EE)

val LightGrey = Color(0xFFD8D8D8)
val DarkGrey = Color(0xFF2A2A2A)

val StarColor = Color(0xFFFFC94D)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)


@Immutable
data class CustomColorsPalette(
    val welcomeScreenBackgroundColor: Color = Color.Unspecified,
    val titleColor: Color = Color.Unspecified,
    val descriptionColor: Color = Color.Unspecified,
    val activeIndicatorColor: Color = Color.Unspecified,
    val inactiveColor: Color = Color.Unspecified,
    val buttonBackgroundColor: Color = Color.Unspecified,
    val topAppbarContentColor: Color = Color.Unspecified,
    val topAppbarBackgroundColor: Color = Color.Unspecified,
    val shimmerEffectCardColor: Color = Color.Unspecified,
    val shimmerEffectPlaceholderColor: Color = Color.Unspecified
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val OnLightCustomColorsPalette = CustomColorsPalette(
    welcomeScreenBackgroundColor = Color.White,
    titleColor = DarkGrey,
    descriptionColor = DarkGrey.copy(alpha = 0.5f),
    activeIndicatorColor = Purple500,
    inactiveColor = LightGrey,
    buttonBackgroundColor = Purple500,
    topAppbarContentColor = Color.White,
    topAppbarBackgroundColor = Purple500,
    shimmerEffectCardColor = ShimmerLightGray,
    shimmerEffectPlaceholderColor = ShimmerMediumGray
)

val OnDarkCustomColorsPalette = CustomColorsPalette(
    welcomeScreenBackgroundColor = Color.Black,
    titleColor = LightGrey,
    descriptionColor = LightGrey.copy(alpha = 0.5f),
    activeIndicatorColor = Purple700,
    inactiveColor = DarkGrey,
    buttonBackgroundColor = Purple700,
    topAppbarContentColor = LightGrey,
    topAppbarBackgroundColor = Color.Black,
    shimmerEffectCardColor = Color.Black,
    shimmerEffectPlaceholderColor = ShimmerDarkGray
)


val MaterialTheme.customColorsPalette: CustomColorsPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColorsPalette.current