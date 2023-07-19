package com.mbah1.jephthah.fieldservicereporter.features.service_timer

data class ServiceTimerState(
    val seconds: String = "00",
    val minutes: String = "00",
    val hours: String = "00",
    val isTimerRunning: Boolean = false
)
