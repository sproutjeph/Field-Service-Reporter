package com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case

import com.mbah1.jephthah.fieldservicereporter.features.service_report.data.repository.ServiceReportRepository
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport

class DeleteServiceReport(
    private val serviceReportRepository: ServiceReportRepository
) {
    suspend operator fun invoke(serviceReport: ServiceReport) {
        serviceReportRepository.deleteServiceReport(serviceReport)
    }
}