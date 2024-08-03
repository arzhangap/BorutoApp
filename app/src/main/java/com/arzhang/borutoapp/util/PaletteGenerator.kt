package com.arzhang.borutoapp.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
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

}

