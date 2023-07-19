package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun OrderBySection(
    modifier: Modifier = Modifier,



    ) {
    val options = listOf("Date", "Last Added", "Year")
    var selectedOption by remember { mutableStateOf(options[0]) }


    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding( vertical = 4.dp),
    ){
        Text(
            text = "SORT BY",
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            options.forEach { option ->
                Row(
                    Modifier
                        .selectable(
                            onClick = { selectedOption = option },
                            selected = (option == selectedOption)
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = when(option){
                            "Date" -> null
                            "Year" -> null
                            else -> null
                        },

                        )
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(start = 4.dp)
                    )
                }
            }
        }
    }

}

