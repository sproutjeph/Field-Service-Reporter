package com.mbah1.jephthah.fieldservicereporter.features.settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.settings.presentation.components.AppVersionSettingsItem
import com.mbah1.jephthah.fieldservicereporter.features.settings.presentation.components.NotificationSettingsItem
import com.mbah1.jephthah.fieldservicereporter.features.settings.presentation.components.SectionSpacer
import com.mbah1.jephthah.fieldservicereporter.features.settings.presentation.components.SelectServiceDays
import com.mbah1.jephthah.fieldservicereporter.features.settings.presentation.components.ThemeSettingsItem

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,

) {

    val context = LocalContext.current

    val settingsViewModel: SettingsScreenViewModel = hiltViewModel()
    val userSelectedThem = settingsViewModel.settingsUiState.collectAsState().value.themeOption

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Divider()

        NotificationSettingsItem(
            title = stringResource(id = R.string.settings_enable_notification),
        )
        Divider()


        SelectServiceDays()
        Divider()

        ThemeSettingsItem(
            selectedTheme = userSelectedThem,
            onOptionSelected = {
                settingsViewModel.setTheme(it)
            }
        )
        Divider()


        SectionSpacer()
        AppVersionSettingsItem(appVersion = stringResource(id = R.string.app_version))



    }
}






@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}