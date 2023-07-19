package com.mbah1.jephthah.fieldservicereporter.features.schedule

import androidx.compose.runtime.MutableState
import java.util.UUID

data class ScheduleInputState(
    val id: UUID? = null,
    val scheduleDate: MutableState<String>,
    val scheduleTime: MutableState<String>,
    val scheduleInfo: MutableState<String>,

)
