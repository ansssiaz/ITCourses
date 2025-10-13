package com.ansssiaz.itcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ansssiaz.component.theme.ITCoursesTheme
import com.ansssiaz.component.theme.White
import com.ansssiaz.feature.log_in.LogInScreen
import com.ansssiaz.feature.main.MainScreen
import com.ansssiaz.shared.domain.CoursesRepository
import com.example.ui_components.Destination.Account
import com.example.ui_components.BottomNavigationBar
import com.example.ui_components.Destination.Favourites
import com.example.ui_components.Destination.Main
import com.example.ui_components.Destination.LogIn
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ITCoursesTheme {
                val navController = rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        val showBottomBar = when (currentDestination?.route) {
                            Main::class.qualifiedName -> true
                            Favourites::class.qualifiedName -> true
                            Account::class.qualifiedName -> true
                            else -> false
                        }

                        if (showBottomBar) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = LogIn,
                        modifier = Modifier.padding(innerPadding),
                        enterTransition = {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                        },
                        exitTransition = {
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
                        },
                        popEnterTransition = {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                        },
                        popExitTransition = {
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
                        }
                    ) {
                        composable<LogIn> {
                            LogInScreen(
                                navController = navController
                            )
                        }

                        composable<Main> {
                            MainScreen()
                        }

                        composable<Favourites> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Favourites screen", color = White)
                            }
                        }

                        composable<Account> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Account screen", color = White)
                            }
                        }
                    }
                }
            }
        }
    }
}