package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report.ServiceReportInputTextFieldState
import java.time.LocalDate
import java.time.Month

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterEditReport(
    modifier: Modifier = Modifier,
    serviceReportInputTextFieldStateState: ServiceReportInputTextFieldState,
    widthSizeClass: WindowWidthSizeClass
){
    val currentYear = LocalDate.now().year
    val options = mutableListOf<String>()

    val months = Month.values().map { it.name }
    for(month in months){
        options.add("$month $currentYear")
    }


    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { serviceReportInputTextFieldStateState.month }



    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 16.dp),

    ){
        item{
            ServiceReportInputField(
                value = serviceReportInputTextFieldStateState.name.value,
                label = R.string.name_input_label,
                modifier = Modifier
                    .then(
                        if(widthSizeClass == WindowWidthSizeClass.Compact){
                            Modifier.fillMaxWidth()

                        }else{
                            Modifier.width(500.dp)

                        }
                    ),

                keyboardType = KeyboardType.Text,
                onValueChange = {
                    if(it.all {
                                char -> char.isLetter() || char.isWhitespace()
                        }) serviceReportInputTextFieldStateState.name.value = it

                }


            )

        }

        item {
            Row (
                modifier = modifier
                    .padding(vertical = 8.dp)
                    .then(
                        if(widthSizeClass == WindowWidthSizeClass.Compact){
                            Modifier.fillMaxWidth()

                        }else{
                            Modifier.width(500.dp)

                        }
                    ),
            ) {


                ExposedDropdownMenuBox(
                    expanded = expanded,
                    modifier = Modifier
                        .weight(1f),
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {

                    ServiceReportInputField(
                        readOnly = true,
                        value = selectedOptionText,
                        onValueChange = {
                            serviceReportInputTextFieldStateState.month.value = it
                        },
                        modifier = Modifier.menuAnchor(),

                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )

                        },
                        label = R.string.month_input_label
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        options.forEach { selectionOption ->

                            DropdownMenuItem(
                                onClick = {
                                    selectedOptionText = selectionOption
                                    expanded = false
                                },
                                text = { Text(selectionOption) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))
                ServiceReportInputField(
                    value = serviceReportInputTextFieldStateState.placement.value,
                    label = R.string.placement_input_label,
                    modifier = Modifier
                        .weight(1f),
                    onValueChange = {

                        if(it.all {
                                    char -> char.isDigit() || char.isWhitespace()
                            }) serviceReportInputTextFieldStateState.placement.value = it

                    }
                )
            }

        }

        item {
            Row (
                modifier = Modifier
                    .then(
                        if(widthSizeClass == WindowWidthSizeClass.Compact){
                            Modifier.fillMaxWidth()

                        }else{
                            Modifier.width(500.dp)

                        }
                    )



            ) {
                ServiceReportInputField(
                    value = serviceReportInputTextFieldStateState.videoShowing.value,
                    label = R.string.video_input_label,
                    modifier = Modifier
                        .weight(1f),
                    onValueChange = {
                        if(it.all {
                                    char -> char.isDigit() || char.isWhitespace()
                            }) serviceReportInputTextFieldStateState.videoShowing.value = it


                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                ServiceReportInputField(
                    value = serviceReportInputTextFieldStateState.hours.value,
                    label = R.string.hours_input_label,
                    modifier = Modifier
                        .weight(1f),
                    onValueChange = {
                        if(it.all {
                                    char -> char.isDigit() || char.isWhitespace()
                            }) serviceReportInputTextFieldStateState.hours.value = it

                    }
                )

            }
        }



        item {
            Row (
                modifier = Modifier
                    .then(
                        if(widthSizeClass == WindowWidthSizeClass.Compact){
                            Modifier.fillMaxWidth()

                        }else{
                            Modifier.width(500.dp)

                        }
                    )
                    .padding(vertical = 8.dp)

            ) {
                ServiceReportInputField(
                    value = serviceReportInputTextFieldStateState.returnVisits.value,
                    label = R.string.return_visits_input_label,
                    modifier = Modifier
                        .weight(1f),
                    onValueChange = {
                        if(it.all {
                                    char -> char.isDigit() || char.isWhitespace()
                            }) serviceReportInputTextFieldStateState.returnVisits.value = it

                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                ServiceReportInputField(
                    value= serviceReportInputTextFieldStateState.bibleStudies.value,
                    label = R.string.bible_studies_input_label,
                    modifier = Modifier
                        .weight(1f),
                    onValueChange = {
                        if(it.all {
                                    char -> char.isDigit() || char.isWhitespace()
                            }) serviceReportInputTextFieldStateState.bibleStudies.value = it

                    }
                )
            }
        }



        item {
            ServiceReportInputField(
                value = serviceReportInputTextFieldStateState.comments.value,
                label = R.string.comments_input_label_label,
                modifier = Modifier
                    .then(
                        if(widthSizeClass == WindowWidthSizeClass.Compact){
                            Modifier.fillMaxWidth()

                        }else{
                            Modifier.width(500.dp)

                        }
                    )
                    .padding(bottom = 20.dp),
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    if(it.all {
                                char -> char.isLetter() || char.isWhitespace()
                        }) serviceReportInputTextFieldStateState.comments.value = it

                }

            )
        }





    }
}

//@Composable
//fun GridViewEnterEditReport() {
//
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(minSize = 128.dp)
//    ) {
//        items(photos) { photo ->
//            PhotoItem(photo)
//        }
//    }
//
//}
