package com.kuuuurt.spacex.compose.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.kuuuurt.spacex.compose.R
import com.kuuuurt.spacex.compose.presentation.features.HistoricalEvents
import com.kuuuurt.spacex.compose.presentation.features.Launches
import com.kuuuurt.spacex.compose.presentation.theme.SpaceXTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXTheme {
                Home()
            }
        }
    }
}

enum class Destinations(val route: String) {
    Launches("launches"),
    History("history")
}

private val TopLevelDestinations = listOf(
    Destinations.Launches to R.drawable.vector_rocket_launch,
    Destinations.History to R.drawable.vector_history
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Home() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SpaceX") },
            )
        },
        bottomBar = {
            val currentRoute = currentRoute(navController)
            val isInTopLevelDestination = currentRoute in TopLevelDestinations.map { it.first.route }

            AnimatedVisibility(visible = isInTopLevelDestination) {
                BottomNavigation {
                    TopLevelDestinations.forEach { (destination, icon) ->
                        BottomNavigationItem(
                            icon = {
                                Image(
                                    painter = painterResource(id = icon),
                                    contentDescription = destination.name,
                                    colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                                )
                            },
                            label = { Text(destination.name) },
                            selected = currentRoute == destination.route,
                            onClick = {
                                // This if check gives us a "singleTop" behavior where we do not create a
                                // second instance of the composable if we are already on that destination
                                if (currentRoute != destination.route) {
                                    navController.navigate(destination.route)
                                }
                            },
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.Gray
                        )
                    }
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding(),
                start = it.calculateStartPadding(LayoutDirection.Ltr),
                end = it.calculateEndPadding(LayoutDirection.Ltr)
            )
        ) {
            NavHost(navController, startDestination = Destinations.Launches.route) {
                composable(Destinations.Launches.route) {
                    Launches()
                }
                composable(Destinations.History.route) {
                    HistoricalEvents()
                }
            }
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}