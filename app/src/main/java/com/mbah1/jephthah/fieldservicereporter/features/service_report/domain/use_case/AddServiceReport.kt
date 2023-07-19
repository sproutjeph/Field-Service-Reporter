package com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case

import com.mbah1.jephthah.fieldservicereporter.features.service_report.data.repository.ServiceReportRepository
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.InvalidReportException
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport

class AddServiceReport(
    private val serviceReportRepository: ServiceReportRepository
) {
    @Throws(Exception::class, InvalidReportException::class)
    suspend operator fun invoke(serviceReport: ServiceReport) {
        if(serviceReport.name.isBlank()){
            throw InvalidReportException("The name of the report can't be empty")
        }

        if(serviceReport.month.isBlank()){
            throw InvalidReportException("The Month of this report can't be empty")
        }
        serviceReportRepository.addServiceReport(serviceReport)
    }
}