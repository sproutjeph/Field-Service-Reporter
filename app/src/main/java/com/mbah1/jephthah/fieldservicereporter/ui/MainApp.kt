package com.mbah1.jephthah.fieldservicereporter.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.mbah1.jephthah.fieldservicereporter.ui.components.ServiceReportFAB
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.Navigation
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.BottomNavigationBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.DestinationTopBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.RailNavigationBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination
import com.mbah1.jephthah.fieldservicereporter.ui.screens.HomeNavigationDrawer
import com.mbah1.jephthah.fieldservicereporter.ui.theme.ServiceReportBackground
import kotlinx.coroutines.launch

@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    onNavigationSelected: (destination: Destination) -> Unit,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
    onNavigateUp: () -> Unit,
    drawerState: DrawerState,
    startDestination: String,
    navController: NavHostController,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    ServiceReportBackground {

        HomeNavigationDrawer(
            modifier = modifier,
            drawerState = drawerState,
            navigateToAddEditReport = {navController.navigate(Destination.AddEditReport.path)},
            onNavigationSelected = onNavigationSelected,
            navigateToSettingsScreen = {navController.navigate(Destination.Settings.path)}
        ){
            Scaffold(
                modifier = Modifier,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                snackbarHost = { SnackbarHost(snackbarHostState) },
                topBar = {
                    DestinationTopBar(
                        currentDestination = currentDestination,
                        onOpenDrawer = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        onNavigateUp = onNavigateUp

                    )


                },

                bottomBar = {
                    BottomNavigationBar(
                        currentDestination = currentDestination,
                        onNavigate = onNavigate
                    )
                },

            ) {paddingValues->

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {

                    if (widthSizeClass != WindowWidthSizeClass.Compact) {
                        RailNavigationBar(
                            currentDestination = currentDestination,
                            onNavigate = onNavigate
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Navigation(
                            modifier = Modifier,
                            startDestination = startDestination,
                            widthSizeClass = widthSizeClass,
                            navController = navController,
                            currentDestination = currentDestination
                        )

                    }

                }
            }
        }



    }


}