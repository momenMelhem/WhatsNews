package com.example.whatsnews.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.whatsnews.R
import com.example.whatsnews.domain.model.Article
import com.example.whatsnews.navigation.Route
import com.example.whatsnews.presentation.bookmark.BookmarkScreen
import com.example.whatsnews.presentation.bookmark.BookmarkViewModel
import com.example.whatsnews.presentation.details.DetailsScreen
import com.example.whatsnews.presentation.details.DetailsViewModel
import com.example.whatsnews.presentation.home.HomeScreen
import com.example.whatsnews.presentation.home.HomeViewModel
import com.example.whatsnews.presentation.news_navigator.components.BottomNavigationItem
import com.example.whatsnews.presentation.news_navigator.components.NewsBottomNavigation
import com.example.whatsnews.presentation.search.SearchScreen
import com.example.whatsnews.presentation.search.SearchViewModel

@Composable
fun NewsNavigator(){
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(R.drawable.ic_home,"Home"),
            BottomNavigationItem(R.drawable.ic_search,"Search"),
            BottomNavigationItem(R.drawable.ic_bookmark,"Bookmark"),
            )
    }
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = remember (key1 = backStackState) {
        when(backStackState?.destination?.route){
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    val isBottomBarVisible = remember (key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(navController, Route.HomeScreen.route)
                            1 -> navigateToTab(navController, Route.SearchScreen.route)
                            2 -> navigateToTab(navController, Route.BookmarkScreen.route)
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier
                .padding(bottom = bottomPadding)
        ){
            composable(route=Route.HomeScreen.route){
                val viewModel:HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(navController= navController, route = Route.SearchScreen.route )
                    },
                    navigateToDetails = {article ->
                        navigateToDetails(
                            navController= navController,
                            article = article
                        )
                    }
                )
            }
            composable(route=Route.SearchScreen.route){
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state ,
                    event = viewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetails(navController= navController, article = it)
                    }
                )
            }
            composable(route = Route.DetailsScreen.route){
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                    DetailsScreen(
                        article = article,
                        event = viewModel::onEvent,
                        navigateUp = {navController.navigateUp()},
                        sideEffect = viewModel.sideEffect
                    )
                }
            }
            composable(route = Route.BookmarkScreen.route){
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    state = state,
                    navigateToDetails = {article ->
                        navigateToDetails(navController = navController, article = article)
                    }
                )
            }
        }
    }

}
private fun navigateToTab (navController: NavController,route: String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let {homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}
private fun navigateToDetails(navController: NavController,article: Article){

    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}