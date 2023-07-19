package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReportActionButton(
    text:String,
    onClick: ()-> Unit,
    enabled: Boolean = true,
){
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )

    ) {
        Text(text)
    }
}