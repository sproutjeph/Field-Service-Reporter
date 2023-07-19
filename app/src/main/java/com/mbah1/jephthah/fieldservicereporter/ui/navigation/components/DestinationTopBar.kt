package com.mbah1.jephthah.fieldservicereporter.ui.navigation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination

@Composable
fun DestinationTopBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigateUp: ()-> Unit,
    onOpenDrawer: ()-> Unit,
) {
    if(currentDestination.isRootDestination) {
        RootDestinationTopBar(
            modifier = modifier,
            currentDestination = currentDestination,
            openDrawer = onOpenDrawer,
        )
    }else{
        ChildDestinationTopBar(
            modifier = modifier,
            onNavigateUp = onNavigateUp,
            title = currentDestination.path
        )
    }

}
