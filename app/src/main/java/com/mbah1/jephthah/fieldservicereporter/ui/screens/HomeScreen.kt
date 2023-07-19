package com.mbah1.jephthah.fieldservicereporter.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.ServiceReportEvent
import com.mbah1.jephthah.fieldservicereporter.features.service_timer.ServiceTimerEvents
import com.mbah1.jephthah.fieldservicereporter.features.service_timer.ServiceTimerState
import com.mbah1.jephthah.fieldservicereporter.ui.components.ServiceReportFAB
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

const val twitterUrl = "https://www.twitter.com/JephthahMbah"
const val linkedInUrl = "https://www.linkedin.com/in/jephthah-mbah-jw-71244263/"
const val emailUrl = "mailto:jephthah.mbah@outlook.com"
const val phoneUrl = "tel:+2347065406165"


@Composable
fun HomeScreen(
    serviceReports: List<ServiceReport>,
    widthSizeClass: WindowWidthSizeClass,
    handleServiceTimerEvents: (events: ServiceTimerEvents)->Unit,
    serviceTimerState: ServiceTimerState,
    navigateToAddEditReportScreenWithArgs: (reportId: String) -> Unit,
    navigateToAddEditReportScreen: ()-> Unit,
    handleServiceReportEvents: (ServiceReportEvent) -> Unit,
    ) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val currentReport = serviceReports.find { it.month == getCurrentMonthAndYear() }

    Scaffold(
        modifier = Modifier,

        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            ServiceReportFAB {
                navigateToAddEditReportScreen()
            }
        }

    ) { contentPadding ->
        HomeContent(
            handleServiceTimerEvents = handleServiceTimerEvents,
            serviceTimerState = serviceTimerState,
            widthSizeClass = widthSizeClass,
            onDeleteClicked = {

                scope.launch {
                    val result =  snackbarHostState.showSnackbar(
                        message = "Delete This Report?",
                        actionLabel = "Yes",
                        withDismissAction = true
                    )

                    if(result == SnackbarResult.ActionPerformed){
                        currentReport?.let {
                            ServiceReportEvent.DeleteServiceReport(
                                it
                            )
                        }?.let { handleServiceReportEvents(it) }

                    }
                }


            },
            navigateToAddEditReportScreenWithArgs = navigateToAddEditReportScreenWithArgs,
            currentReport = currentReport
        )

    }



}


fun launchSocialActivity(context: Context, socialType: String) {
    val intent = when (socialType) {
        "linkedin" -> Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl))
        "twitter" -> Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl))
        "mailto:" -> Intent(Intent.ACTION_VIEW, Uri.parse(emailUrl))
        "tel:" -> Intent(Intent.ACTION_VIEW, Uri.parse(phoneUrl))
        else -> Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl))
    }
    context.startActivity(intent)
}

fun getCurrentMonthAndYear(): String {
    val currentDate = LocalDate.now()
    val monthYearFormat = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH)
    return currentDate.format(monthYearFormat).uppercase(Locale.ROOT)
}
