package com.mbah1.jephthah.fieldservicereporter.features.service_timer

sealed class ServiceTimerEvents{
    object OnStart: ServiceTimerEvents()
    object OnPause: ServiceTimerEvents()
    object OnStop: ServiceTimerEvents()
}
