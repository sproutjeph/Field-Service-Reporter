package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReportRow(
    text:String,
    value:String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(8.dp)
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        ) {

            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .height(40.dp)
                    .border(1.dp, color = MaterialTheme.colorScheme.onTertiaryContainer)
            )
            Text(
                text = value,
                modifier = Modifier
                    .padding(8.dp)
                    .width(29.dp)

            )
        }
    }

}