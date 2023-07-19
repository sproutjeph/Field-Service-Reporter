package com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.use_case

import com.mbah1.jephthah.fieldservicereporter.features.schedule.data.ScheduleRepository
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel
import kotlinx.coroutines.flow.Flow

class GetAllSchedules(
    private val scheduleRepository: ScheduleRepository
) {
    operator fun invoke(): Flow<List<ScheduleModel>> {
        return scheduleRepository.getAllSchedules()
    }
}


