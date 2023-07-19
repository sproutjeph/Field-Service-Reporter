package com.mbah1.jephthah.fieldservicereporter.features.service_timer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

@HiltViewModel
class ServiceTimerViewModel  @Inject constructor (): ViewModel() {

    val serviceTimerUiState = MutableStateFlow(ServiceTimerState())

    private var time: Duration = Duration.ZERO

    private lateinit var timer: Timer



    private var isTimerRunning by mutableStateOf(false)



   private fun start() = viewModelScope.launch {
        timer = fixedRateTimer(initialDelay = 1000L, period = 1000L){
            time = time.plus(Duration.ofSeconds(1))
            updateTimeStates()
        }

        isTimerRunning = true
        serviceTimerUiState.value = serviceTimerUiState.value.copy(isTimerRunning = true)
    }



    private fun updateTimeStates()  {
        val seconds = time.seconds % 60
        val minutes = time.toMinutes() % 60
        val hours = time.toHours()

        serviceTimerUiState.value = serviceTimerUiState.value.copy(
            seconds = seconds.toString().padStart(2,'0'),
            minutes = minutes.toString().padStart(2,'0'),
            hours = hours.toString().padStart(2,'0')
        )
    }

   private fun pause() = viewModelScope.launch{
        timer.cancel()
        isTimerRunning = false
        serviceTimerUiState.value = serviceTimerUiState.value.copy(isTimerRunning = false)
    }

   private fun stop() = viewModelScope.launch{
        pause()
        time = Duration.ZERO
        updateTimeStates()
    }

    fun onEvent(event: ServiceTimerEvents){
        when(event){
            ServiceTimerEvents.OnStart -> start()
            ServiceTimerEvents.OnPause -> pause()
            ServiceTimerEvents.OnStop -> stop()
        }
    }



}

