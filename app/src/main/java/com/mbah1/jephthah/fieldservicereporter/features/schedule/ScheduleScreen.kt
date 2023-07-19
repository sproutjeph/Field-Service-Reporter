package com.mbah1.jephthah.fieldservicereporter.features.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.schedule.components.Calendar
import com.mbah1.jephthah.fieldservicereporter.features.schedule.components.ScheduleDateTimePicker
import com.mbah1.jephthah.fieldservicereporter.features.schedule.components.ScheduleDisplayContainer
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel
import com.mbah1.jephthah.fieldservicereporter.ui.components.ServiceReportFAB
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.BottomNavigationBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.RailNavigationBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.ServiceReportTopAppBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination


@Composable
fun ScheduleScreen(
    navigateUp: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    currentDestination: Destination,
    schedules:  List<ScheduleModel>,
    onNavigate: (destination: Destination) -> Unit,
    onAddSchedule: (scheduleModel: ScheduleModel) -> Unit,
    onDeleteSchedule: (scheduleModel: ScheduleModel) -> Unit,
    selectedScheduleDate:String,
    setSelectScheduleDate: (selectedScheduleDate: String)-> Unit


) {
    val openDialog = remember { mutableStateOf(false) }





    val snackbarHostState = remember { SnackbarHostState() }

    val calendarState: SheetState = rememberSheetState()
    val clockState:SheetState = rememberSheetState()
    val selectedTime = remember { mutableStateOf("") }
    val selectedDate = remember { mutableStateOf("") }

    val selectedSchedules = schedules.filter { it.scheduleDate == selectedScheduleDate }




    Scaffold (

        floatingActionButton = {
            ServiceReportFAB(
                icon = Icons.Default.Add,
                onClicked = {
                    openDialog.value = true

                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },

        ){ contentPadding->

        if(widthSizeClass == WindowWidthSizeClass.Compact){
            Column(
                modifier = Modifier
                    .padding(contentPadding)
            ) {
                Calendar(
                    selectedScheduleDate = selectedScheduleDate,
                    setSelectScheduleDate = setSelectScheduleDate
                )

                ScheduleDisplayContainer(
                    selectedSchedules = selectedSchedules,
                    onDeleteSchedule = onDeleteSchedule
                )

            }
        }else{
            Row(
                modifier = Modifier
                    .padding(top = contentPadding.calculateTopPadding(),  end = 16.dp)
                    .fillMaxWidth()
            ) {
                RailNavigationBar(
                    modifier = Modifier,
                    currentDestination = currentDestination,
                    onNavigate = onNavigate,
                )
                Spacer(modifier = Modifier.width(24.dp))


                Calendar(
                    modifier = Modifier.weight(1f),
                    selectedScheduleDate = selectedScheduleDate,
                    setSelectScheduleDate = setSelectScheduleDate
                )
                
                Spacer(modifier = Modifier.width(16.dp))

                ScheduleDisplayContainer(
                    modifier = Modifier.weight(1f)
                        .padding(top= 65.dp),
                    selectedSchedules = selectedSchedules,
                    onDeleteSchedule = onDeleteSchedule
                )
            }

        }




            EnterScheduleDialog(
                openDialog = openDialog,
                snackbarHostState = snackbarHostState,
                calendarState = calendarState,
                selectedDate = selectedDate,
                selectedTime = selectedTime,
                clockState = clockState,
                onAddSchedule = onAddSchedule,
            )
            ScheduleDateTimePicker(
                calendarState = calendarState,
                clockState = clockState,
                selectedDate = selectedDate,
                selectedTime = selectedTime
            )

        }

    }








