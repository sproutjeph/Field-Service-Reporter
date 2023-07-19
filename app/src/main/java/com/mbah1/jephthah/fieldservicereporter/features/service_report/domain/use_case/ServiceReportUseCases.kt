package com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case

data class ServiceReportUseCases(
    val getAllServiceReports: GetAllServiceReports,
    val deleteServiceReport: DeleteServiceReport,
    val getServiceReport: GetServiceReport,
    val addServiceReport: AddServiceReport,
    val upDateServiceReport: UpdateServiceReport,
)