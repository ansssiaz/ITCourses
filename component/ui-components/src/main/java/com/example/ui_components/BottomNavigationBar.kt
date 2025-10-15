package com.example.ui_components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ansssiaz.component.theme.DarkGray
import com.ansssiaz.component.theme.Green
import com.ansssiaz.component.theme.LightGray
import com.ansssiaz.component.theme.Stroke
import com.ansssiaz.component.theme.White
import kotlinx.serialization.Serializable

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        BottomNavItem.MainItem,
        BottomNavItem.FavouritesItem,
        BottomNavItem.AccountItem
    )

    NavigationBar(
        modifier = Modifier
            .drawBehind {
                drawLine(
                    color = Stroke,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx()
                )
            },
        containerColor = DarkGray
    ) {
        items.forEach { item ->
            val itemRoute = item.destination::class.qualifiedName ?: ""
            val isSelected = currentDestination?.route == itemRoute

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.title)
                    )
                },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(item.destination) {
                            popUpTo(Destination.Main::class.qualifiedName ?: "") {
                                saveState = true
                                inclusive = false
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Green,
                    selectedTextColor = Green,
                    unselectedIconColor = White,
                    unselectedTextColor = White,
                    indicatorColor = LightGray
                )
            )
        }
    }
}

sealed class BottomNavItem(
    val destination: Any,
    val icon: Int,
    val title: Int
) {
    object MainItem : BottomNavItem(
        destination = Destination.Main,
        icon = R.drawable.house,
        title = R.string.main
    )

    object FavouritesItem : BottomNavItem(
        destination = Destination.Favourites,
        icon = R.drawable.bookmark,
        title = R.string.favourites
    )

    object AccountItem : BottomNavItem(
        destination = Destination.Account,
        icon = R.drawable.person,
        title = R.string.account
    )
}

sealed interface Destination {
    @Serializable
    object LogIn

    @Serializable
    object Main

    @Serializable
    object Favourites

    @Serializable
    object Account
}