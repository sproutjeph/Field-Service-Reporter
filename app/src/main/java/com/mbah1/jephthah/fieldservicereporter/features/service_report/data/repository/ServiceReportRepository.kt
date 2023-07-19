package com.mbah1.jephthah.fieldservicereporter.features.service_report.data.repository

import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport
import kotlinx.coroutines.flow.Flow

interface ServiceReportRepository {
    fun getAllServiceReports(): Flow<List<ServiceReport>>

    suspend fun addServiceReport(serviceReport: ServiceReport)

    suspend fun deleteAllServiceReports()

    suspend fun deleteServiceReport(serviceReport: ServiceReport)

    suspend fun getServiceReportById(serviceReportId:String): ServiceReport?

    suspend fun updateServiceReport(serviceReport: ServiceReport)

}