package com.mbah1.jephthah.fieldservicereporter.features.schedule.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleDisplay(
    scheduleModel: ScheduleModel,
    onDeleteSchedule: (scheduleModel: ScheduleModel)-> Unit

) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)


        ) {

            Text(
                text =  "- ${to12HourFormat(scheduleModel.scheduleTime)}",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text =  "- ${scheduleModel.scheduleInfo}",
                maxLines = 2,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize

            )


        }

        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(8.dp)
                .clickable(onClick = {
                    onDeleteSchedule(scheduleModel)
                }),

        )

    }



}







fun to12HourFormat(time: String): String {
    return if( time.split(":").first().toInt() < 12){
        "$time AM"
    }else{
        val timeFormatter24 = DateTimeFormatter.ofPattern("HH:m")
        val timeFormatter12 = DateTimeFormatter.ofPattern("hh:mm a")
        val parsedTime = LocalTime.parse(time, timeFormatter24)
        parsedTime.format(timeFormatter12)
    }

}