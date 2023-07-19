package com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.use_case

import com.mbah1.jephthah.fieldservicereporter.features.schedule.data.ScheduleRepository

class GetSchedule(
    private val scheduleRepository: ScheduleRepository
) {
    suspend operator fun invoke(scheduleId: String) {
        scheduleRepository.getScheduleById(scheduleId)
    }
}