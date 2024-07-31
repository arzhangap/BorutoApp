package com.arzhang.borutoapp.presentation.common

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.arzhang.borutoapp.R
import com.arzhang.borutoapp.domain.model.Hero
import com.arzhang.borutoapp.navigation.Screen
import com.arzhang.borutoapp.presentation.components.RatingWidget
import com.arzhang.borutoapp.presentation.components.ShimmerEffect
import com.arzhang.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import com.arzhang.borutoapp.ui.theme.LARGE_PADDING
import com.arzhang.borutoapp.ui.theme.MEDIUM_PADDING
import com.arzhang.borutoapp.ui.theme.SMALL_PADDING
import com.arzhang.borutoapp.util.Constants.BASE_URL

@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val result = handlePagingResult(heroes = heroes, modifier = modifier)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
            modifier = modifier
        ) {
            items(
                count = heroes.itemCount,
                key = heroes.itemKey { it.id },
                contentType = heroes.itemContentType { "contentType" }
            ) { index ->
                val hero = heroes[index]
                hero?.let {
                    HeroItem(hero = hero, navController = navController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Hero>,
    modifier: Modifier = Modifier
) : Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error ->  loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error ->  loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect(modifier = modifier)
                false
            }
            error != null -> {
                EmptyScreen(error = error, heroes = heroes, modifier = modifier)
                false
            }
            heroes.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HeroItem(
    hero:Hero,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val painter = rememberImagePainter(data = "$BASE_URL${hero.image}") {
        placeholder(R.drawable.placeholder)
        error(R.drawable.placeholder)
    }

    Box(modifier = modifier
        .height(HERO_ITEM_HEIGHT)
        .clickable {
            navController.navigate(Screen.Details.passHeroId(hero.id))
        },
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.hero_image),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.41f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.5f),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = hero.name,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top= SMALL_PADDING),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    RatingWidget(
                        rating = hero.rating,
                        modifier = Modifier.padding(end = SMALL_PADDING)
                    )
                    Text(text = "(${hero.rating})", textAlign = TextAlign.Center, color = Color.White.copy(alpha = 0.5f))
                }

            }
        }
    }
}

@Preview
@Composable
private fun HeroItemPreview() {
    HeroItem(hero = Hero(
        id = 1,
        name = "sasuke",
        image = "",
        about = "about",
        rating = 4.5,
        power = 100,
        month = "",
        day = "",
        family = emptyList(),
        abilities = emptyList(),
        natureTypes = emptyList()
    ), navController = rememberNavController())
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun HeroItemDarkPreview() {
    HeroItem(hero = Hero(
        id = 1,
        name = "sasuke",
        image = "",
        about = "about",
        rating = 4.5,
        power = 100,
        month = "",
        day = "",
        family = emptyList(),
        abilities = emptyList(),
        natureTypes = emptyList()
    ), navController = rememberNavController())
}