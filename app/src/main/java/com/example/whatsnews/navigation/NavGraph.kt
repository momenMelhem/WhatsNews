package com.example.whatsnews.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatsnews.presentation.bookmark.BookmarkViewModel
import com.example.whatsnews.presentation.bookmark.BookmarkScreen
import com.example.whatsnews.presentation.news_navigator.NewsNavigator

@Composable
fun NavGraph(
    startDestination:String,
    navController:NavHostController = rememberNavController()
){
    NavHost(navController,startDestination){
        composable(Route.HomeScreen.route){
            NewsNavigator()
        }
    }
}