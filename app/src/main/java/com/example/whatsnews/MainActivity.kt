package com.example.whatsnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.whatsnews.navigation.NavGraph
import com.example.whatsnews.navigation.Route
import com.example.whatsnews.ui.theme.WhatsNewsTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            WhatsNewsTheme {
                val isDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(
                        Color.Transparent,
                        darkIcons = !isDarkMode
                    )
                }
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background).fillMaxSize()) {

                    NavGraph(startDestination = Route.HomeScreen.route )
                }
            }
        }
    }
}

