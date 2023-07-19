package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report

import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport


sealed class AddEditServiceReportEvents{
    data class OnUpdate(val serviceReport: ServiceReport): AddEditServiceReportEvents()
    data class OnAdd(val serviceReport: ServiceReport): AddEditServiceReportEvents()

}


