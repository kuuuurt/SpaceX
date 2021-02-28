package com.kuuuurt.spacex.compose.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.kuuuurt.spacex.compose.R
import com.kuuuurt.spacex.compose.presentation.features.launches.Launches
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

enum class Sections(
    val route: String,
    @DrawableRes val icon: Int
) {
    Launches("Launches", R.drawable.vector_rocket_launch),
    Rockets("Rockets", R.drawable.vector_rocket)
}

private val Destinations = listOf(
    Sections.Launches,
    Sections.Rockets
)

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
            BottomNavigation {
                val currentRoute = currentRoute(navController)
                Sections.values().forEach {
                    BottomNavigationItem(
                        icon = {
                            Image(
                                painter = painterResource(id = it.icon),
                                contentDescription = it.name,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                            )
                        },
                        label = { Text(it.route) },
                        selected = currentRoute == it.route,
                        onClick = {
                            // This if check gives us a "singleTop" behavior where we do not create a
                            // second instance of the composable if we are already on that destination
                            if (currentRoute != it.route) {
                                navController.navigate(it.route)
                            }
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Gray
                    )
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
            NavHost(navController, startDestination = Sections.Launches.route) {
                composable(Sections.Launches.route) {
                    Launches()
                }
                composable(Sections.Rockets.route) {
                    Text("Rockets!!!")
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