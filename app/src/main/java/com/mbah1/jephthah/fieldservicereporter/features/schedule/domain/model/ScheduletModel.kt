package com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date
import java.util.UUID


@Entity(tableName = "schedule_tbl")
data class ScheduleModel(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "schedule_date")
    val scheduleDate: String,

    @ColumnInfo(name = "schedule_time")
    val scheduleTime: String,

    @ColumnInfo(name = "schedule_info")
    val scheduleInfo: String,

    @ColumnInfo(name = "schedule_entry_date")
    val entryDate: Date = Date.from(Instant.now())
)