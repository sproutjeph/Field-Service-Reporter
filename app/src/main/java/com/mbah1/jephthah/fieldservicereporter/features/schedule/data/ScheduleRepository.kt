package com.mbah1.jephthah.fieldservicereporter.features.schedule.data

import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getAllSchedules(): Flow<List<ScheduleModel>>

    suspend fun addSchedule(scheduleModel: ScheduleModel)

    suspend fun deleteAllSchedules()

    suspend fun deleteSchedule(scheduleModel: ScheduleModel)

    suspend fun getScheduleById(scheduleId:String): ScheduleModel?

    suspend fun updateSchedule(scheduleModel: ScheduleModel)
}