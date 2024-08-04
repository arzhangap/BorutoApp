package com.arzhang.borutoapp.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

object PaletteGenerator {

    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ) : Bitmap? {
        val loader = ImageLoader(context = context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            // if you don't set this to true you may face errors
            .allowHardware(false)
            .build()
        val imageResult = loader.execute(request)
        return if(imageResult is SuccessResult) {
            (imageResult.drawable as BitmapDrawable).bitmap
        } else {
            null
        }
    }

    fun extractColorsFromBitmap(bitmap: Bitmap) : Map<String, String> {
        return mapOf(
            "vibrant" to parseColorSwatch(
                color = Palette.from(bitmap).generate().vibrantSwatch
            ),
            "darkVibrant" to parseColorSwatch(
                color = Palette.from(bitmap).generate().darkVibrantSwatch
            ),
            "onDarkVibrant" to parseBodyColor(
                color = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor
            ),
        )
    }

    private fun parseColorSwatch(color: Palette.Swatch?) : String {
        return if(color != null) {
            val parsedColor = Integer.toHexString(color.rgb)
            return "#$parsedColor"
        } else {
            "#000000"
        }
    }

    private fun parseBodyColor(color: Int?): String {
        return if(color != null) {
            val parseColor = Integer.toHexString(color)
            return "#$parseColor"
        } else {
            "#FFFFFF"
        }
    }

}

