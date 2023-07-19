package com.mbah1.jephthah.fieldservicereporter.ui.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootDestinationTopBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    openDrawer: ()-> Unit,
) {

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(text = currentDestination.title) },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = null
                )

            }

        }
    )

}

@Preview
@Composable
fun Preview_RootDestinationTopBar() {
    RootDestinationTopBar(
        currentDestination = Destination.Home,
        openDrawer = {}
    )
}