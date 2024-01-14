package com.example.whatsnews.navigation

sealed class Route (
    val route:String
){
    data object HomeScreen : Route("home")
    data object SearchScreen : Route(route = "searchScreen")
    data object DetailsScreen : Route(route = "detailsScreen")
    data object BookmarkScreen : Route(route = "bookMarkScreen")


}