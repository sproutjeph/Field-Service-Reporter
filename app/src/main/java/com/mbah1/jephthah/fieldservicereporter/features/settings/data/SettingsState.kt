package com.mbah1.jephthah.fieldservicereporter.features.settings.data

data class SettingsState(
    val notificationsEnabled: Boolean = false,
    val themeOption: Theme = Theme.SYSTEM
)
