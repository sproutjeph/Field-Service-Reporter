package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case.ServiceReportUseCases
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.util.OrderType
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.util.ReportOrder
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.ServiceReportEvent
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.ServiceReportState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceReportViewModel @Inject constructor(
    private val serviceReportUseCases: ServiceReportUseCases
) : ViewModel(){

    private val _state = mutableStateOf(ServiceReportState())
    val state: State<ServiceReportState> = _state

    private var getServiceReportJob:Job? = null





    init {

        getServiceReports(ReportOrder.Date(OrderType.Descending))

    }

    fun onEvent(event: ServiceReportEvent){
        when(event){
            is ServiceReportEvent.Order -> {
                if(state.value.reportOrder::class == event.reportOrder::class &&
                    state.value.reportOrder.orderType == event.reportOrder.orderType){
                    return
                }
                getServiceReports(reportOrder = event.reportOrder)
            }

            is ServiceReportEvent.DeleteServiceReport -> {
                viewModelScope.launch {
                    serviceReportUseCases.deleteServiceReport(event.serviceReport)
                }
            }

            is ServiceReportEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

        }
    }



    private fun getServiceReports(reportOrder: ReportOrder){
        getServiceReportJob?.cancel()
        getServiceReportJob = serviceReportUseCases.getAllServiceReports(reportOrder)
            .onEach {serviceReport->
            _state.value = state.value.copy(
                report = serviceReport,
                reportOrder = reportOrder
            )

            }.launchIn(viewModelScope)
    }


}
