package com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case

import com.mbah1.jephthah.fieldservicereporter.features.service_report.data.repository.ServiceReportRepository
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.util.OrderType
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.util.ReportOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllServiceReports(
    private val serviceReportRepository: ServiceReportRepository
) {
    operator fun invoke(reportOrder: ReportOrder = ReportOrder.Date(OrderType.Descending))
    : Flow<List<ServiceReport>> {
        return serviceReportRepository.getAllServiceReports() .map {reports->
            when(reportOrder.orderType){
                is OrderType.Ascending -> {
                    when(reportOrder){
                        is ReportOrder.Date -> reports.sortedBy { it.entryDate }
                        is ReportOrder.Month -> reports.sortedBy { it.month }
                    }
                }

                is OrderType.Descending -> {
                    when(reportOrder){
                        is ReportOrder.Date -> reports.sortedByDescending { it.entryDate }
                        is ReportOrder.Month -> reports.sortedByDescending { it.month }
                    }
                }
            }
        }
    }
}