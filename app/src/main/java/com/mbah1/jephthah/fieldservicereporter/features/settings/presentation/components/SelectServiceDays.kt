package com.mbah1.jephthah.fieldservicereporter.features.settings.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.settings.presentation.SettingsScreenViewModel

@Composable
fun SelectServiceDays(
    modifier: Modifier = Modifier,

) {
    val context = LocalContext.current

    val settingsScreenViewModel: SettingsScreenViewModel = viewModel()


    val sharedPreferences = remember {settingsScreenViewModel.getSharedPreferences(context) }
    var selectedDaysOfWeek by remember { mutableStateOf(settingsScreenViewModel.getSelectedDaysOfWeek(sharedPreferences)) }

    val daysOfWeek =  context.resources.getStringArray(R.array.days_of_week)
    SettingsItem(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding( 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.select_days_text),
                modifier = Modifier,

            )
            daysOfWeek.forEach{ day ->
                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .toggleable(
                            value = day in selectedDaysOfWeek,
                            onValueChange = {
                                selectedDaysOfWeek = if (it) {
                                    selectedDaysOfWeek + day
                                } else {
                                    selectedDaysOfWeek - day
                                }
                               settingsScreenViewModel.saveSelectedDaysOfWeek(sharedPreferences, selectedDaysOfWeek)
                                settingsScreenViewModel.scheduleNotification(context, selectedDaysOfWeek)
                            }
                        ),

                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(
                        text = day,
                        modifier = Modifier
                            .weight(1f)
                    )

                    Checkbox(
                        checked = day in selectedDaysOfWeek,
                        onCheckedChange = null
                    )




                }
            }


        }
    }


}




@Preview(showBackground = true)
@Composable
fun SelectServiceDaysPreview() {
    SelectServiceDays(

    )
}













