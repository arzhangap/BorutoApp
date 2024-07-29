package com.arzhang.borutoapp.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.paging.LoadState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arzhang.borutoapp.R
import com.arzhang.borutoapp.ui.theme.DarkGrey
import com.arzhang.borutoapp.ui.theme.LightGrey
import com.arzhang.borutoapp.ui.theme.NETWORK_ERROR_HEIGHT
import com.arzhang.borutoapp.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error) {
    val message by remember {
        mutableStateOf(parseError(error = error))
    }
    val icon by remember {
        mutableIntStateOf(R.drawable.network_error)
    }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(NETWORK_ERROR_HEIGHT)
                .alpha(alphaAnim),
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.network_error_icon),
            tint = if(isSystemInDarkTheme()) LightGrey else DarkGrey
        )
        Text(
            modifier = Modifier.padding(SMALL_PADDING)
                .alpha(alphaAnim),
            text = message,
            color = if (isSystemInDarkTheme()) LightGrey else DarkGrey,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
    }
}

fun parseError(error: LoadState.Error): String {
    return when(error.error) {
        is SocketTimeoutException -> {
            "SERVER UNAVAILABLE"
        }

        is ConnectException -> {
            "INTERNET UNAVAILABLE"
        }

        else -> {
            "UNKNOWN ERROR"
        }
    }
}