package com.arzhang.borutoapp.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen(route = "splash_screen")
    data object Welcome : Screen(route = "welcome_screen")
    data object Home : Screen(route = "home_screen")
    data object Details : Screen(route = "details_screen/{heroId}") {
        fun passHeroId(heroId: Int) : String {
            return "details_screen/$heroId"
        }
    }
    data object Search : Screen(route = "search_screen")
}