package com.mbah1.jephthah.fieldservicereporter.ui.navigation.model

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.util.Locale

class NavigationBarItem(
    val selected: Boolean,
    val onClicked: () -> Unit,
    val icon: @Composable () -> Unit,
    val label: @Composable () -> Unit
) {

    companion object {
        fun buildNavigationItems(
            currentDestination: Destination,
            onNavigate: (destination: Destination) -> Unit
        ): List<NavigationBarItem> {
            return listOf(
                Destination.Home,
                Destination.Students,
                Destination.Reports,
                Destination.Interests,
                Destination.Schedule
            ).map {destination->
                NavigationBarItem(
                    selected = currentDestination.path == destination.path,
                    label = {
                        Text(
                            text = destination.path.replaceFirstChar { char ->
                                char.titlecase(Locale.getDefault())
                            },
                        )
                    },
                    icon = {
                        destination.icon?.let { icon ->
                            Icon(
                                imageVector = icon,
                                contentDescription = null
                            )
                        }
                    },

                    onClicked = { onNavigate(destination) },

                    )

            }

        }
    }
}