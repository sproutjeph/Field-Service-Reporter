package com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.use_case

import com.mbah1.jephthah.fieldservicereporter.features.schedule.data.ScheduleRepository
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel

class UpdateSchedule(
    private val scheduleRepository: ScheduleRepository
) {
    suspend operator fun invoke(scheduleModel: ScheduleModel) {
        scheduleRepository.updateSchedule(scheduleModel)
    }
}