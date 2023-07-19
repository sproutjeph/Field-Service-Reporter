package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report.components.EnterEditReport
import com.mbah1.jephthah.fieldservicereporter.ui.components.ServiceReportFAB
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.ServiceReportTopAppBar
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun AddEditServiceReportScreen(
    serviceReportInputTextFieldStateState: ServiceReportInputTextFieldState,
    id: UUID? = null,
    widthSizeClass: WindowWidthSizeClass,
    navigateUp: () -> Unit,
    onAddReport:(serviceReport: ServiceReport) -> Unit,
    onEditReport:(serviceReport: ServiceReport) -> Unit,
    showSnackbar: Boolean,
    ) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(

        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            ServiceReportFAB(icon = Icons.Default.Done) {

                if (id != null && !showSnackbar) {
                    onEditReport(
                        ServiceReport(
                            id = id,
                            name = serviceReportInputTextFieldStateState.name.value,
                            month = serviceReportInputTextFieldStateState.month.value,
                            placement = serviceReportInputTextFieldStateState.placement.value,
                            videoShowing = serviceReportInputTextFieldStateState.videoShowing.value,
                            hours = serviceReportInputTextFieldStateState.hours.value,
                            returnVisits = serviceReportInputTextFieldStateState.returnVisits.value,
                            bibleStudies = serviceReportInputTextFieldStateState.bibleStudies.value,
                            comments = serviceReportInputTextFieldStateState.comments.value,
                        )
                    )


                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Report Updated View Report?",
                            actionLabel = "Yes",
                            withDismissAction = true
                        )

                        if (result == SnackbarResult.ActionPerformed) {
                            navigateUp()
                        }

                    }


                } else if (showSnackbar
                    || serviceReportInputTextFieldStateState.name.value.isBlank()
                    || serviceReportInputTextFieldStateState.month.value.isBlank()
                ) {

                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Name and Month are required",
                            withDismissAction = true
                        )
                    }
                } else {

                    onAddReport(
                        ServiceReport(
                            name = serviceReportInputTextFieldStateState.name.value,
                            month = serviceReportInputTextFieldStateState.month.value,
                            placement = serviceReportInputTextFieldStateState.placement.value,
                            videoShowing = serviceReportInputTextFieldStateState.videoShowing.value,
                            hours = serviceReportInputTextFieldStateState.hours.value,
                            returnVisits = serviceReportInputTextFieldStateState.returnVisits.value,
                            bibleStudies = serviceReportInputTextFieldStateState.bibleStudies.value,
                            comments = serviceReportInputTextFieldStateState.comments.value,
                        )
                    )
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Report Added View Report?",
                            actionLabel = "Yes",
                            withDismissAction = true
                        )

                        if (result == SnackbarResult.ActionPerformed) navigateUp()
                    }


                }



                serviceReportInputTextFieldStateState.name.value = ""
                serviceReportInputTextFieldStateState.month.value = ""
                serviceReportInputTextFieldStateState.placement.value = ""
                serviceReportInputTextFieldStateState.videoShowing.value = ""
                serviceReportInputTextFieldStateState.hours.value = ""
                serviceReportInputTextFieldStateState.returnVisits.value = ""
                serviceReportInputTextFieldStateState.bibleStudies.value = ""
                serviceReportInputTextFieldStateState.comments.value = ""

            }

        }
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            EnterEditReport(
                serviceReportInputTextFieldStateState = serviceReportInputTextFieldStateState,
                widthSizeClass = widthSizeClass,
            )
        }

    }

}

