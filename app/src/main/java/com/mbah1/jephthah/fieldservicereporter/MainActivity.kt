package com.mbah1.jephthah.fieldservicereporter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mbah1.jephthah.fieldservicereporter.ui.theme.FieldServiceReporterTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.mbah1.jephthah.fieldservicereporter.ui.MainApp
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val darkTheme = isSystemInDarkTheme()

            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)



            val navBackStackEntry = navController.currentBackStackEntryAsState()

            val currentDestination by remember {
                derivedStateOf {
                    Destination.fromString(navBackStackEntry.value?.destination?.route)
                }

            }
            // Update the dark content of the system bars to match the theme
            DisposableEffect(systemUiController, darkTheme) {
                systemUiController.systemBarsDarkContentEnabled = !darkTheme
                onDispose {}
            }
            FieldServiceReporterTheme {
                val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass

                MainApp(
                    widthSizeClass = widthSizeClass,
                    onNavigationSelected = {},
                    currentDestination = currentDestination,
                    onNavigate = {
                        navController.navigate(it.path) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onNavigateUp = { navController.popBackStack() },
                    drawerState = drawerState,
                    startDestination = getStartDestination(),
                    navController = navController
                )


            }
        }
    }
}


private fun getStartDestination(): String {
    return Destination.Home.path
}



