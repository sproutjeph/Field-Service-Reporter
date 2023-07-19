package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports

import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.util.OrderType
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.util.ReportOrder

data class ServiceReportState(
    val report: List<ServiceReport> = emptyList(),
    val reportOrder: ReportOrder = ReportOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false

)
