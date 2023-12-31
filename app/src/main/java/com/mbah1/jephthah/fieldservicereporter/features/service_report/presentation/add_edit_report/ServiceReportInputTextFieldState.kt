package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report

import androidx.compose.runtime.MutableState
import java.util.UUID

data class ServiceReportInputTextFieldState(
    val id: UUID? = null,
    val name: MutableState<String>,
    val month: MutableState<String>,
    val placement: MutableState<String>,
    val videoShowing: MutableState<String>,
    val hours: MutableState<String>,
    val returnVisits: MutableState<String>,
    val bibleStudies: MutableState<String>,
    val comments: MutableState<String>,
)