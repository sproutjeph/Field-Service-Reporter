package com.mbah1.jephthah.fieldservicereporter.ui.navigation.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceReportTopAppBar(
    title: String,
    isMainScreen: Boolean = true,
    navigateUp:() -> Unit,
    onOpenDrawer: () -> Unit= {},
) {

    val showDropDownMenu = remember { mutableStateOf(false) }

//    if (showDropDownMenu.value) {
//        SettingsDropDownMenu(
//            showDropDownMenu = showDropDownMenu,
//        )
//
//    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = Modifier
            .background(color = Color.LightGray),
        navigationIcon = {
            if (isMainScreen) {
                IconButton(onClick = {
                    onOpenDrawer.invoke()
                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")

                }
            } else {

                IconButton(onClick = { navigateUp.invoke()}) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Icon")

                }
            }

        },

        actions = {
            IconButton(onClick = { showDropDownMenu.value = !showDropDownMenu.value }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Settings Icon")

            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            titleContentColor = MaterialTheme.colorScheme.primary

        )
    )

}