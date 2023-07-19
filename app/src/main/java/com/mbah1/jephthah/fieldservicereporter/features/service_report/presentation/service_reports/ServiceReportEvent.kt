package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports

import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.util.ReportOrder

sealed class ServiceReportEvent{
    data class Order(val reportOrder: ReportOrder): ServiceReportEvent()
    data class DeleteServiceReport(val serviceReport: ServiceReport): ServiceReportEvent()
    object ToggleOrderSection: ServiceReportEvent()

}