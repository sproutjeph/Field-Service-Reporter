package com.mbah1.jephthah.fieldservicereporter.ui.navigation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.NavigationBarItem.Companion.buildNavigationItems
import com.mbah1.jephthah.fieldservicereporter.ui.theme.FieldServiceReporterTheme

@Composable
fun RailNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        content = {
            buildNavigationItems(
                currentDestination = currentDestination,
                onNavigate = onNavigate

            ).forEach {destination->
                NavigationRailItem(
                    selected = destination.selected,
                    onClick = destination.onClicked,
                    icon = destination.icon,
                    label = destination.label,
                )

            }
        }
    )

}

@Preview
@Composable
fun Preview_RailNavigationBar() {
    FieldServiceReporterTheme {
        RailNavigationBar(
            currentDestination = Destination.Home,
            onNavigate = {},
        )
    }

}