package com.mbah1.jephthah.fieldservicereporter.features.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.SheetState
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report.components.ServiceReportInputField
import kotlinx.coroutines.launch

@Composable
fun EnterScheduleDialog(
    openDialog: MutableState<Boolean>,
    scheduleInputState: ScheduleInputState = ScheduleInputState(
        scheduleDate = mutableStateOf(""),
        scheduleTime = mutableStateOf(""),
        scheduleInfo = mutableStateOf(""),
    ),
    snackbarHostState: SnackbarHostState,
    calendarState: SheetState,
    clockState: SheetState,
    selectedDate: MutableState<String>,
    selectedTime: MutableState<String>,
    onAddSchedule: (scheduleModel: ScheduleModel) -> Unit


    ) {
    val scope = rememberCoroutineScope()
    scheduleInputState.scheduleDate.value = selectedDate.value
    scheduleInputState.scheduleTime.value = selectedTime.value


    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {

                openDialog.value = false
            },

            title = { Text("Enter Your Schedule") },
            text = {
                Column {

                    Button(
                        onClick = {
                                calendarState.show()
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text("Select Date")

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Date: ${selectedDate.value}")

                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                                  clockState.show()
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text("Select Time")

                    }
                    Spacer(modifier = Modifier.height(8.dp))

                        Text(text = "Time: ${selectedTime.value}")




                    ServiceReportInputField(
                        value = scheduleInputState.scheduleInfo.value,
                        onValueChange = {scheduleInputState.scheduleInfo.value = it},
                        label = R.string.schedule_details,
                        keyboardType = KeyboardType.Text,
                        maxLine = 5,

                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {

                    if(scheduleInputState.scheduleDate.value.isEmpty() || scheduleInputState.scheduleInfo.value.isEmpty()){
                        scope.launch {
                            snackbarHostState.showSnackbar("Fill all fields", "Dismiss")
                        }

                        return@TextButton


                    }else{

                        onAddSchedule(
                            ScheduleModel(
                                scheduleDate = scheduleInputState.scheduleDate.value,
                                scheduleTime = scheduleInputState.scheduleTime.value,
                                scheduleInfo = scheduleInputState.scheduleInfo.value
                            )
                        )


                        scope.launch {
                            snackbarHostState.showSnackbar("Schedule Added", "Dismiss")
                        }
                        scheduleInputState.scheduleDate.value = ""
                        scheduleInputState.scheduleTime.value = ""
                        scheduleInputState.scheduleInfo.value = ""
                        openDialog.value = false

                    }

                }) {
                    Text("Save")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Dismiss")
                }
            },

            )
    }
}