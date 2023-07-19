package com.mbah1.jephthah.fieldservicereporter.features.schedule.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.Month
import java.util.Locale


@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    selectedScheduleDate: String,
    setSelectScheduleDate: (selectedScheduleDate: String) -> Unit
) {
    var selectedYear by remember { mutableIntStateOf(LocalDate.now().year) }
    var selectedMonth by remember { mutableStateOf(LocalDate.now().month) }

    var chosenYear by remember { mutableIntStateOf(LocalDate.now().year) }
    var chosenMonth by remember { mutableStateOf(LocalDate.now().month.name) }

    var expanded by remember { mutableStateOf(false) }
    var expandedMonth by remember { mutableStateOf(false) }



    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surface)
    ) {

        val firstDayOfMonth = LocalDate.of(selectedYear, selectedMonth, 1)
        val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value

        Row {
            Row(modifier = Modifier.padding(16.dp, 8.dp)) {
                Text(
                    text = "Select a year:",
                   modifier = Modifier.clickable { expanded = true },
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text =  chosenYear.toString(),
                   modifier = Modifier.clickable { expanded = true }
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                for (year in 2023 downTo 1960) {
                    DropdownMenuItem(
                        text = {
                            Text(text = year.toString())
                        },
                        onClick = {
                            selectedYear = year
                            expanded = false
                            chosenYear = year
                        })
                }
            }
        }
        Row {
            Row(modifier = Modifier.padding(16.dp, 8.dp)) {
                Text(
                    "Select a month:",
                    Modifier.clickable { expandedMonth = true },
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(chosenMonth, Modifier.clickable { expandedMonth = true })
            }

            DropdownMenu(
                expanded = expandedMonth,
                onDismissRequest = { expandedMonth = false }
            ) {
                for (month in Month.values()) {
                    DropdownMenuItem(
                        text = {
                            Text(month.name.lowercase()
                                .replaceFirstChar {
                                    if (it.isLowerCase()) {
                                        it.titlecase(Locale.ROOT)
                                    } else it.toString()
                                }
                            )
                        },
                        onClick = {
                        selectedMonth = month
                        expandedMonth = false
                        chosenMonth = month.name
                    })
                }
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface))
        ) {
            val weekdays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
            weekdays.forEach {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                )
            }
        }
        Box(
            Modifier
                .padding(16.dp, 0.dp)
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface))
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(7), modifier = Modifier
                    .height(195.dp)
                    .padding(0.dp, 8.dp)
            ) {
                val emptyCells = List(firstDayOfWeek - 1) { null }
                val daysInMonth = firstDayOfMonth.lengthOfMonth()
                items(emptyCells.size + daysInMonth + 2) { dayOfMonth ->
                    val day = dayOfMonth - emptyCells.size + 1
                    val textColor =
                        if ((day + firstDayOfWeek - 3) % 7 == 4 ||
                            (day + firstDayOfWeek - 3) % 7 == 5
                        ) {
                            MaterialTheme.colorScheme.error
                        } else MaterialTheme.colorScheme.onSurface

                    val backgroundColor: Color =
                        if (day == selectedScheduleDate.split("/")[0].toInt()) {
                            MaterialTheme.colorScheme.tertiaryContainer
                        } else Color.Transparent
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = if (day <= 0 || day > daysInMonth) " " else day.toString(),
                            textAlign = TextAlign.Center,
                            color = textColor,
                            modifier = Modifier
                                .background(backgroundColor)
                                .width(32.dp)
                                .height(32.dp)
                                .clickable(
                                    enabled = !(day <= 0 || day > daysInMonth),
                                    onClick = {
                                        setSelectScheduleDate("${day}/${selectedMonth}/${selectedYear}")
                                    }
                                ),
                        )
                    }


                }
            }
        }


    }


}










