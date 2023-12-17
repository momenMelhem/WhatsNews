package com.example.whatsnews.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.whatsnews.presentation.home.HomeScreen
import com.example.whatsnews.presentation.home.HomeViewModel
import com.example.whatsnews.presentation.search.SearchScreen
import com.example.whatsnews.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination:String,
    navController:NavHostController = rememberNavController()
){
    NavHost(navController,startDestination){
        composable(Route.HomeScreen.route){
            val viewModel : SearchViewModel = hiltViewModel()
            SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigate = {})
        }
    }
}