package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.InvalidReportException
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case.ServiceReportUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditServiceReportViewModel @Inject constructor(
    private val serviceReportUseCases: ServiceReportUseCases,
): ViewModel() {




    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event: AddEditServiceReportEvents){
        when(event){
            is AddEditServiceReportEvents.OnAdd ->{
                addServiceReport(event.serviceReport)
            }
            is AddEditServiceReportEvents.OnUpdate -> {
                updateServiceReport(event.serviceReport)
            }
        }
    }


    private fun addServiceReport(serviceReport: ServiceReport) =
        viewModelScope.launch {
            try {
                serviceReportUseCases.addServiceReport(serviceReport)

                _eventFlow.emit(UiEvent.AddReport)
            } catch (e: InvalidReportException) {
                _eventFlow.emit(
                    UiEvent.ShowSnackBar(
                        message = e.message ?: "Couldn't save Report"
                    )
                )
            }
        }

    private fun updateServiceReport(serviceReport: ServiceReport) = viewModelScope.launch {
        serviceReportUseCases.upDateServiceReport(serviceReport)
    }



    sealed class UiEvent{
        data class ShowSnackBar(val message: String): UiEvent()
        object AddReport: UiEvent()
    }


}