package com.mbah1.jephthah.fieldservicereporter.features.schedule.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query("SELECT * from schedule_tbl")
    fun getAllSchedules(): Flow<List<ScheduleModel>>

    @Query("SELECT * from schedule_tbl where id =:id")
    suspend fun getScheduleById(id:String): ScheduleModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSchedule(scheduleModel: ScheduleModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSchedule(scheduleModel: ScheduleModel)

    @Query("DELETE from schedule_tbl")
    suspend  fun deleteAllSchedules()

    @Delete
    suspend fun deleteSchedule(scheduleModel: ScheduleModel)
}