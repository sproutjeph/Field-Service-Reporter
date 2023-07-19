package com.mbah1.jephthah.fieldservicereporter.features.schedule.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel

@Composable
fun ScheduleDisplayContainer(
    modifier: Modifier = Modifier,
    selectedSchedules: List<ScheduleModel>,
    onDeleteSchedule:(scheduleModel: ScheduleModel)-> Unit

) {

    Column(
        modifier = modifier,
    ) {
        Text(
            text= stringResource(id = R.string.schedule_details),
            modifier = Modifier.padding(16.dp, 6.dp),
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            Modifier
                .height(350.dp)
                .padding(16.dp, 8.dp)
                .fillMaxWidth()
        ) {
            items(selectedSchedules) {schedule ->
                ScheduleDisplay(
                    scheduleModel = schedule,
                    onDeleteSchedule = onDeleteSchedule
                )
            }
        }
    }

}