package com.arzhang.borutoapp.presentation.components

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import com.arzhang.borutoapp.ui.theme.ABOUT_PLACEHOLDER_HEIGHT
import com.arzhang.borutoapp.ui.theme.EXTRA_SMALL_PADDING
import com.arzhang.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import com.arzhang.borutoapp.ui.theme.LARGE_PADDING
import com.arzhang.borutoapp.ui.theme.MEDIUM_PADDING
import com.arzhang.borutoapp.ui.theme.NAME_PLACEHOLDER_HEIGHT
import com.arzhang.borutoapp.ui.theme.RATING_PLACEHOLDER_HEIGHT
import com.arzhang.borutoapp.ui.theme.SMALL_PADDING
import com.arzhang.borutoapp.ui.theme.customColorsPalette

@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
        modifier = modifier
    ) {
        items(count = 2) {
            AnimatedShimmerItem()
        }
    }
}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()
    val alphaAnim by transition.animateFloat(
        initialValue =1f,
        targetValue =0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animation"
    )
    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(alpha: Float) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(HERO_ITEM_HEIGHT),
        color = MaterialTheme.customColorsPalette.shimmerEffectCardColor,
        shape = RoundedCornerShape(LARGE_PADDING)
    ) {
        Column(
            modifier = Modifier.padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                modifier = Modifier
                    .alpha(alpha)
                    .fillMaxWidth(0.5f)
                    .height(NAME_PLACEHOLDER_HEIGHT),
                color = MaterialTheme.customColorsPalette.shimmerEffectPlaceholderColor,
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ) {}
            Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
            repeat(3) {
                Surface(
                    modifier = Modifier
                        .alpha(alpha)
                        .fillMaxWidth()
                        .height(ABOUT_PLACEHOLDER_HEIGHT),
                    color = MaterialTheme.customColorsPalette.shimmerEffectPlaceholderColor,
                    shape = RoundedCornerShape(size = SMALL_PADDING)
                ) {}
                Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(5) {
                    Surface(
                        modifier = Modifier
                            .alpha(alpha)
                            .size(RATING_PLACEHOLDER_HEIGHT),
                        color = MaterialTheme.customColorsPalette.shimmerEffectPlaceholderColor,
                        shape = RoundedCornerShape(size = EXTRA_SMALL_PADDING)
                    ) {}
                    Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
                }
            }
        }
    }
}

@Preview
@Composable
private fun ShimmerItemPreview() {
    ShimmerItem(alpha = 1f)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShimmerItemDarkPreview() {
    ShimmerItem(alpha = 1f)
}