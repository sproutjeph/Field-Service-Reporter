package com.mbah1.jephthah.fieldservicereporter.features.service_report.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mbah1.jephthah.fieldservicereporter.features.schedule.data.ScheduleDao
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.students.data.data_source.StudentDao
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel
import com.mbah1.jephthah.fieldservicereporter.ui.utils.DateConverter
import com.mbah1.jephthah.fieldservicereporter.ui.utils.UUIDConverter

@Database(
    entities = [ServiceReport::class, StudentModel::class, ScheduleModel::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class ServiceReportDatabase : RoomDatabase() {
    abstract val serviceReportDao: ServiceReportDao
    abstract val studentDao: StudentDao
    abstract val scheduleDao: ScheduleDao

    companion object {
        const val DATABASE_NAME = "service_report_db"
    }
}