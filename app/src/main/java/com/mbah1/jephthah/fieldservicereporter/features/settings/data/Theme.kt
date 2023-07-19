package com.mbah1.jephthah.fieldservicereporter.features.settings.data

import androidx.annotation.StringRes
import com.mbah1.jephthah.fieldservicereporter.R

enum class Theme(@StringRes val label: Int) {
    LIGHT(R.string.theme_light),
    DARK(R.string.theme_dark),
    SYSTEM(R.string.theme_system)
}