package com.mbah1.jephthah.fieldservicereporter.ui.components

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat

@Composable
fun StatusBar() {
    val context = LocalContext.current
    val window = (context as? Activity)?.window
    window?.statusBarColor = Color.Transparent.toArgb()

    SideEffect {
        val controller = window?.decorView?.let { WindowCompat.getInsetsController(window, it) }
        controller?.isAppearanceLightStatusBars = true
    }
}