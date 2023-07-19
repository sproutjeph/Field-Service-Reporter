package com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model

import androidx.compose.ui.graphics.toArgb
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mbah1.jephthah.fieldservicereporter.ui.theme.BabyBlue
import com.mbah1.jephthah.fieldservicereporter.ui.theme.LightGreen
import com.mbah1.jephthah.fieldservicereporter.ui.theme.RedOrange
import com.mbah1.jephthah.fieldservicereporter.ui.theme.RedPink
import com.mbah1.jephthah.fieldservicereporter.ui.theme.Violet
import java.time.Instant
import java.util.Date
import java.util.UUID

@Entity(tableName = "report_tbl")
data class ServiceReport(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "publishers_name")
    val name: String,

    @ColumnInfo(name = "month")
    val month: String,

    @ColumnInfo(name = "placement")
    val placement: String,

    @ColumnInfo(name = "videoShowing")
    val videoShowing: String,

    @ColumnInfo(name = "hours")
    val hours: String,

    @ColumnInfo(name = "returnVisits")
    val returnVisits: String,

    @ColumnInfo(name = "bibleStudies")
    val bibleStudies: String,

    @ColumnInfo(name = "comments")
    val comments: String,

    val color: Int = reportColors.random().toArgb(),

    @ColumnInfo(name = "report_entry_date")
    val entryDate: Date = Date.from(Instant.now())
){
    companion object{
        val reportColors = listOf(
            RedOrange,
            LightGreen,
            Violet,
            BabyBlue,
            RedPink

        )
    }
}

class InvalidReportException(message: String): Exception(message)

