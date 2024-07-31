package com.arzhang.borutoapp.presentation.screens.search

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arzhang.borutoapp.ui.theme.TOP_APP_BAR_HEIGHT
import com.arzhang.borutoapp.ui.theme.customColorsPalette
import kotlin.math.sin

@Composable
fun SearchTopBar(
    paddingValue: PaddingValues,
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onClosedClicked: () -> Unit
) {
    SearchWidget(
        paddingValue = paddingValue,
        modifier = modifier,
        text = text,
        onTextChange = onTextChange,
        onSearchClicked = onSearchClicked,
        onClosedClicked = onClosedClicked
    )
}

@Composable
fun SearchWidget(
    paddingValue: PaddingValues,
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onClosedClicked: () -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shadowElevation = 6.dp,
        color = MaterialTheme.customColorsPalette.topAppbarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth().padding(top = paddingValue.calculateTopPadding()),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.7f),
                    text = "Search here...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(color = MaterialTheme.customColorsPalette.topAppbarContentColor),
            singleLine = true,
            leadingIcon = {
                Icon(
                    modifier = Modifier.alpha(0.8f),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Button",
                    tint = MaterialTheme.customColorsPalette.topAppbarContentColor
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    if(text.isNotEmpty()) onTextChange("") else onClosedClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = MaterialTheme.customColorsPalette.topAppbarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions {
                onSearchClicked(text)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                cursorColor = MaterialTheme.customColorsPalette.topAppbarContentColor
            )
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun searchWidgetPreview() {
    Scaffold(
        topBar = {
            SearchTopBar(
                paddingValue = PaddingValues(),
                text = "",
                onTextChange = {},
                onSearchClicked = {},
                onClosedClicked = {}
            )
        }
    ) {}
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun searchWidgetDarkPreview() {

    Scaffold(
        topBar = {
            SearchTopBar(
                paddingValue = PaddingValues(),
                text = "",
                onTextChange = {},
                onSearchClicked = {},
                onClosedClicked = {}
            )
        }
    ) {}

}