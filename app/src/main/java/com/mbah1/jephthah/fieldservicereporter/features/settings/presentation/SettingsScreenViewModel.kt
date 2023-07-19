package com.mbah1.jephthah.fieldservicereporter.features.settings.presentation


import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import com.mbah1.jephthah.fieldservicereporter.features.settings.data.SettingsState
import com.mbah1.jephthah.fieldservicereporter.features.settings.data.Theme
import com.mbah1.jephthah.fieldservicereporter.features.settings.receiver.NotificationReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat
) : ViewModel() {


    val settingsUiState = MutableStateFlow(SettingsState())


    fun scheduleNotification(context: Context, selectedDays: List<String>){
        if(ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
            ){

            return

        }


        val currentDayOfWeek = LocalDate.now().dayOfWeek
        val currentDay = currentDayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())




        Log.d("CurrentDay", "scheduleNotification: $currentDay")
        Log.d("CurrentDay", "scheduleNotification: $selectedDays")

       // Check if the current day is in the selected days list
        if (currentDay in selectedDays) {

            notificationManager.notify(1, notificationBuilder.build())
        }
    }





    fun setTheme(theme: Theme){
        settingsUiState.value = settingsUiState.value.copy(themeOption = theme)
    }


     fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    }

     fun getSelectedDaysOfWeek(sharedPreferences: SharedPreferences): List<String> {
        val selectedDays = sharedPreferences.getStringSet("selectedDays", emptySet())
        return selectedDays?.toList() ?: emptyList()
    }

     fun saveSelectedDaysOfWeek(sharedPreferences: SharedPreferences, selectedDays: List<String>) {
        sharedPreferences.edit()
            .putStringSet("selectedDays", selectedDays.toSet())
            .apply()
    }






}