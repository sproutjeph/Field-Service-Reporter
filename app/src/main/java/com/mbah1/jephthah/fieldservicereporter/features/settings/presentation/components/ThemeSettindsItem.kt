package com.mbah1.jephthah.fieldservicereporter.features.settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.settings.data.Theme

@Composable
fun ThemeSettingsItem(
    modifier: Modifier = Modifier,
    selectedTheme: Theme,
    onOptionSelected: (option: Theme) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }



    SettingsItem(modifier = modifier) {
        Row(
            modifier = Modifier
                .clickable(
                    onClickLabel = stringResource(id = R.string.cd_select_theme)
                ) {
                    expanded = !expanded

                }
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id =
                R.string.setting_option_theme)
            )
            Text(text = stringResource(id = selectedTheme.label))
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(16.dp, 0.dp)
        ) {
            Theme.values().forEach { theme ->
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = theme.label))
                    },
                    onClick = {
                        onOptionSelected(theme)
                        expanded = false
                    }
                )
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun ThemeSettingsItemPreview() {
    ThemeSettingsItem(
        selectedTheme = Theme.SYSTEM,
        onOptionSelected = {}
    )
}

