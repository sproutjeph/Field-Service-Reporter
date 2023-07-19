package com.mbah1.jephthah.fieldservicereporter.features.schedule

import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel

sealed class ScheduleEvents{
    data class DeleteSchedule(val scheduleModel: ScheduleModel): ScheduleEvents()
    data class AddSchedule(val scheduleModel: ScheduleModel): ScheduleEvents()
    data class SetSelectedScheduleDate(val selectedScheduleDate: String): ScheduleEvents()


}
