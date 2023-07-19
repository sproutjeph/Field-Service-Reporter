package com.mbah1.jephthah.fieldservicereporter.ui.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mbah1.jephthah.fieldservicereporter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildDestinationTopBar(
    modifier: Modifier = Modifier,
    onNavigateUp: ()-> Unit,
    title: String
) {

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_navigate_up)
                )
            }
        }
    )

}

@Preview
@Composable
fun Preview_ChildDestinationTopBar() {
    ChildDestinationTopBar(
        onNavigateUp = {},
        title = "Settings"
    )
}