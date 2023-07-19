package com.mbah1.jephthah.fieldservicereporter.features.schedule

import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel
import java.time.LocalDate

data class ScheduleState(
    val schedules: List<ScheduleModel> = emptyList(),
    val selectedScheduleDate: String = "${LocalDate.now().dayOfMonth}/${LocalDate.now().month.name}/${LocalDate.now().year}"

)
