package com.mbah1.jephthah.fieldservicereporter.ui.utils

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun timeStampFromDate(date: Date):Long{

        return date.time
    }

    @TypeConverter
    fun dateFromTimestamp(timestamp:Long): Date {

        return Date(timestamp)

    }
}