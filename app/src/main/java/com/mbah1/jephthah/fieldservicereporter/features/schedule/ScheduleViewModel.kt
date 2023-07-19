package com.mbah1.jephthah.fieldservicereporter.features.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.use_case.ScheduleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleUseCases: ScheduleUseCases
) : ViewModel(){


    private val _state = mutableStateOf(ScheduleState())
    val state: State<ScheduleState> = _state

    private var getScheduleJob: Job? = null




    private val _showSnackbar = MutableStateFlow(false)
    val showSnackbar: StateFlow<Boolean> = _showSnackbar


    init {
        getSchedules()

    }


    fun onEvent(event: ScheduleEvents){
        when(event){

            is ScheduleEvents.DeleteSchedule -> {
                deleteSchedule(event.scheduleModel)
            }

            is ScheduleEvents.AddSchedule -> {
                addSchedule(event.scheduleModel)
            }
            is ScheduleEvents.SetSelectedScheduleDate ->{
                setSelectScheduleDate(event.selectedScheduleDate)
            }


        }
    }



    private fun getSchedules() {
        getScheduleJob?.cancel()
        getScheduleJob = scheduleUseCases.getAllSchedules()
            .onEach {schedules->
                _state.value = state.value.copy(
                    schedules = schedules
                )
            }.launchIn(viewModelScope)


    }



    private fun addSchedule(scheduleModel: ScheduleModel) = viewModelScope.launch {
        scheduleUseCases.addSchedule(scheduleModel)

    }


    private fun deleteSchedule(scheduleModel: ScheduleModel) = viewModelScope.launch {
        scheduleUseCases.deleteSchedule(scheduleModel)
    }

   private fun setSelectScheduleDate(selectedScheduleDate: String){
        _state.value = state.value.copy(
            selectedScheduleDate = selectedScheduleDate
        )

    }


}




