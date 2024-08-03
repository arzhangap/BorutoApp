package com.arzhang.borutoapp.presentation.screens.details

import android.util.Log
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.arzhang.borutoapp.R
import com.arzhang.borutoapp.domain.model.Hero
import com.arzhang.borutoapp.presentation.components.InfoBox
import com.arzhang.borutoapp.presentation.components.OrderedList
import com.arzhang.borutoapp.ui.theme.ABOUT_TEXT_MAX_LINES
import com.arzhang.borutoapp.ui.theme.CLOSE_ICON_SIZE
import com.arzhang.borutoapp.ui.theme.EXTRA_LARGE_PADDING
import com.arzhang.borutoapp.ui.theme.INFO_ICON_SIZE
import com.arzhang.borutoapp.ui.theme.LARGE_PADDING
import com.arzhang.borutoapp.ui.theme.MEDIUM_PADDING
import com.arzhang.borutoapp.ui.theme.MIN_SHEET_HEIGHT
import com.arzhang.borutoapp.ui.theme.SMALL_PADDING
import com.arzhang.borutoapp.ui.theme.customColorsPalette
import com.arzhang.borutoapp.util.Constants.BASE_URL
import com.arzhang.borutoapp.util.Constants.MIN_BACKGROUND_IMAGE_FRACTION
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedHero: Hero?
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded
        )
    )
    LaunchedEffect(Unit) {
        scaffoldState.bottomSheetState.expand()
    }
    val currentSheetFraction = scaffoldState.currentSheetFraction
    val radiusAnime by animateDpAsState(
        targetValue =
        if(currentSheetFraction >= 0.9f)
            EXTRA_LARGE_PADDING
        else 0.dp,
        label = "corner animation",
        animationSpec = tween(
            easing = FastOutLinearInEasing
        )
    )

    BottomSheetScaffold(
        modifier = Modifier.fillMaxHeight(),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetShape = RoundedCornerShape(
            topStart = radiusAnime,
            topEnd = radiusAnime
        ),
        sheetContent = {
            selectedHero?.let { BottomSheetContent(selectedHero = selectedHero) }
        },
        sheetDragHandle = {}
    ) {
        selectedHero?.let { hero ->
            BackgroundContent(
                heroImage = hero.image,
                imageFraction = currentSheetFraction,
                onCloseClicked = {navController.popBackStack()}
            )
        }
    }
}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.customColorsPalette.titleColor
) {
    Column(
        modifier = Modifier
            .padding(LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = contentColor
            )
            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.bolt),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = stringResource(R.string.power),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = stringResource(R.string.month),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.cake),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = stringResource(R.string.birthday),
                textColor = contentColor
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(0.5f)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            maxLines = ABOUT_TEXT_MAX_LINES
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(
                title = stringResource(R.string.family),
                items = selectedHero.family,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.abilities),
                items = selectedHero.abilities,
                textColor = contentColor
            )
            OrderedList(
                title = stringResource(R.string.nature_types),
                items = selectedHero.natureTypes,
                textColor = contentColor
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 0f,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "$BASE_URL${heroImage}"
    val painter = rememberImagePainter(imageUrl) {
        error(R.drawable.placeholder)
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(backgroundColor)) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                // 1f means full height of parent
                .fillMaxHeight(fraction = imageFraction + MIN_BACKGROUND_IMAGE_FRACTION)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = { onCloseClicked() },
            modifier = Modifier
                .padding(SMALL_PADDING)
                .padding(top = 15.dp)
                .align(Alignment.TopEnd),
        ) {
            Icon(
                modifier = Modifier
                    .size(CLOSE_ICON_SIZE),
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.close_button),
                tint = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    @Composable
    get() {
        val offset =  try {
            bottomSheetState.requireOffset()
        } catch (e: IllegalStateException) {
            0f
        }

        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val bottomSheetHeight =offset.roundToInt().dp - screenHeight
        val fraction = (bottomSheetHeight.value / screenHeight.value)
        val target = bottomSheetState.targetValue
        val current = bottomSheetState.currentValue

        Log.d("Fraction screen", screenHeight.toString())
        Log.d("Fraction offset", offset.toString())
        Log.d("Fraction Current", current.toString())

        return when {
            current == SheetValue.Hidden && target == SheetValue.Hidden -> 1f
            current == SheetValue.Expanded && target == SheetValue.Expanded -> fraction
            current == SheetValue.Hidden && target == SheetValue.Expanded -> 1f - fraction
            current == SheetValue.Expanded && target == SheetValue.Hidden -> 1f + fraction
            else -> fraction
        }

    }

@Preview
@Composable
private fun BottomSheetContentPreview() {
    BottomSheetContent(selectedHero =
    Hero(
        id = 1,
        name = "Arzhang",
        image = "",
        about = "didijidjsidjsd",
        rating = 5.0,
        power = 0,
        month = "Oct",
        day = "22nd",
        family = listOf("test1", "test2", "test3"),
        abilities = listOf("android development", "writing", "pixel art"),
        natureTypes = listOf("Fire", "Earth")
    )
    )

}