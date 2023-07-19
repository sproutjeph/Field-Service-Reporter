package com.mbah1.jephthah.fieldservicereporter.features.settings.presentation.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier
            .heightIn(min = 56.dp)
    ) {
        content()
    }
}


