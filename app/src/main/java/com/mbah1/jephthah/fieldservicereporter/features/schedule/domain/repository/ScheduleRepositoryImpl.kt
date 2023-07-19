package com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.repository

import com.mbah1.jephthah.fieldservicereporter.features.schedule.data.ScheduleDao
import com.mbah1.jephthah.fieldservicereporter.features.schedule.data.ScheduleRepository
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel
import kotlinx.coroutines.flow.Flow

class ScheduleRepositoryImpl(
    private val scheduleDao: ScheduleDao
): ScheduleRepository {
    override fun getAllSchedules(): Flow<List<ScheduleModel>> {
        return scheduleDao.getAllSchedules()
    }

    override suspend fun addSchedule(scheduleModel: ScheduleModel) {
        return scheduleDao.addSchedule(scheduleModel)
    }

    override suspend fun deleteAllSchedules() {
        return scheduleDao.deleteAllSchedules()
    }

    override suspend fun deleteSchedule(scheduleModel: ScheduleModel) {
        return scheduleDao.deleteSchedule(scheduleModel)
    }

    override suspend fun getScheduleById(scheduleId: String): ScheduleModel {
        return scheduleDao.getScheduleById(scheduleId)
    }

    override suspend fun updateSchedule(scheduleModel: ScheduleModel) {
        return scheduleDao.updateSchedule(scheduleModel)
    }

}