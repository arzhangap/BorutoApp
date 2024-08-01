package com.arzhang.borutoapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.arzhang.borutoapp.ui.theme.SMALL_PADDING
import com.arzhang.borutoapp.ui.theme.customColorsPalette

@Composable
fun OrderedList(
    title: String,
    items: List<String>,
    textColor: Color
) {
    Column {
        Text(
            modifier = Modifier.padding(bottom = SMALL_PADDING),
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold
        )
        items.forEachIndexed { index, item ->
            Text(
                modifier = Modifier.alpha(0.5f),
                text = "${index+1} $item",
                color = textColor,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OrderListPreview() {
    OrderedList(
        title ="Family",
        items = listOf("Minato", "kosina"),
        textColor =MaterialTheme.customColorsPalette.titleColor
    )
}