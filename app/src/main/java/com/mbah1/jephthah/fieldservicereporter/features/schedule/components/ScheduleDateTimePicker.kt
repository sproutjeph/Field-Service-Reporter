package com.mbah1.jephthah.fieldservicereporter.features.schedule.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleDateTimePicker(
    calendarState: SheetState,
    clockState: SheetState,
    selectedDate: MutableState<String>,
    selectedTime: MutableState<String>,

    ) {

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,

        ),
        selection = CalendarSelection.Date{ date->
            selectedDate.value= "${date.dayOfMonth}/${date.month}/${date.year}"

        })

    ClockDialog(
        state = clockState,
        config = ClockConfig(
                    is24HourFormat = false,
        ),

        selection = ClockSelection.HoursMinutes { hours, minutes->
            selectedTime.value = "$hours:$minutes"

        }
    )





}