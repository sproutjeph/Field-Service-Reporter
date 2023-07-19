package com.mbah1.jephthah.fieldservicereporter.ui.navigation.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.NavigationBarItem.Companion.buildNavigationItems
import com.mbah1.jephthah.fieldservicereporter.ui.theme.FieldServiceReporterTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {

    BottomAppBar(
        modifier = modifier
    ) {
        buildNavigationItems(
            currentDestination = currentDestination,
            onNavigate = onNavigate

        ).forEach { destination->
            NavigationBarItem(
                selected = destination.selected,
                icon = destination.icon,
                label = destination.label,
                onClick = {destination.onClicked()},
            )

        }




    }

}
@Preview( )
@Composable
fun NavBottomBarPreview() {
    FieldServiceReporterTheme{
        BottomNavigationBar(
            currentDestination = Destination.Home,
            onNavigate = {}
        )
    }

}