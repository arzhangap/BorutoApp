package com.arzhang.borutoapp.domain.model

import androidx.annotation.DrawableRes
import com.arzhang.borutoapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {

    data object First: OnBoardingPage(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Are you a boruto fan? because if you are we have a great news for you"
    )
    data object Second: OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Find your favorite heroes and learn some of the things that you don't know about"
    )
    data object Third: OnBoardingPage(
        image = R.drawable.power,
        title = "Power",
        description = "Check out your heroes power and abilities"
    )

}