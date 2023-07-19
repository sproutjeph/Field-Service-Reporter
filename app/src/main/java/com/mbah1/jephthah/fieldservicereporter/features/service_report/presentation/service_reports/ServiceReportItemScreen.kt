package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.components.OrderBySection
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.components.ServiceReportItem
import com.mbah1.jephthah.fieldservicereporter.ui.components.ServiceReportFAB
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.BottomNavigationBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.RailNavigationBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.ServiceReportTopAppBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination
import kotlinx.coroutines.launch

@Composable
fun ServiceReportItemScreen(
    modifier:Modifier = Modifier,
    navigateToAddEditReportScreenWithArgs: (reportId: String) -> Unit,
    navigateToAddEditReportScreen: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
    serviceReports: List<ServiceReport>,
    handleServiceReportEvents: (ServiceReportEvent) -> Unit,
    navigateUp: () -> Unit

) {


    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }



    Scaffold(
        modifier = Modifier,

        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            ServiceReportFAB {
                navigateToAddEditReportScreen.invoke()
            }
        }

    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            OrderBySection()
            when (widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    LazyColumn {

                        if (serviceReports.isEmpty()) {
                            item {
                                EmptyListUi()
                            }
                        } else {
                            items(serviceReports) { serviceReport ->
                                ServiceReportItem(
                                    serviceReport = serviceReport,
                                    navigateToAddEditReportScreenWithArgs = {
                                        navigateToAddEditReportScreenWithArgs.invoke(serviceReport.id.toString())
                                    },
                                    onDeleteClicked = {

                                        scope.launch {
                                            val result = snackbarHostState.showSnackbar(
                                                message = "Delete This Report?",
                                                actionLabel = "Yes",
                                                withDismissAction = true
                                            )

                                            if (result == SnackbarResult.ActionPerformed) {
                                                handleServiceReportEvents(
                                                    ServiceReportEvent.DeleteServiceReport(
                                                        serviceReport
                                                    )
                                                )

                                            }
                                        }


                                    }
                                )
                            }
                        }
                    }
                }

                WindowWidthSizeClass.Medium -> {
                    Row(
                        modifier = modifier
                            .fillMaxSize()
                    ) {
                        RailNavigationBar(
                            modifier = Modifier,
                            currentDestination = currentDestination,
                            onNavigate = onNavigate,
                        )

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                        ) {

                            items(serviceReports) { serviceReport ->
                                ServiceReportItem(
                                    serviceReport = serviceReport,
                                    navigateToAddEditReportScreenWithArgs = {
                                        navigateToAddEditReportScreenWithArgs.invoke(serviceReport.id.toString())
                                    },
                                    onDeleteClicked = {

                                        scope.launch {
                                            val result = snackbarHostState.showSnackbar(
                                                message = "Delete This Report?",
                                                actionLabel = "Yes",
                                                withDismissAction = true
                                            )

                                            if (result == SnackbarResult.ActionPerformed) {
                                                handleServiceReportEvents(
                                                    ServiceReportEvent.DeleteServiceReport(
                                                        serviceReport
                                                    )
                                                )

                                            }
                                        }


                                    }
                                )
                            }

                        }
                    }




                }

                WindowWidthSizeClass.Expanded -> {
                    Row(
                        modifier = modifier
                            .fillMaxSize()
                    ) {
                        RailNavigationBar(
                            modifier = Modifier,
                            currentDestination = currentDestination,
                            onNavigate = onNavigate,
                        )

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                        ) {

                            items(serviceReports) { serviceReport ->
                                ServiceReportItem(
                                    serviceReport = serviceReport,
                                    navigateToAddEditReportScreenWithArgs = {
                                        navigateToAddEditReportScreenWithArgs.invoke(serviceReport.id.toString())
                                    },
                                    onDeleteClicked = {

                                        scope.launch {
                                            val result = snackbarHostState.showSnackbar(
                                                message = "Delete This Report?",
                                                actionLabel = "Yes",
                                                withDismissAction = true
                                            )

                                            if (result == SnackbarResult.ActionPerformed) {
                                                handleServiceReportEvents(
                                                    ServiceReportEvent.DeleteServiceReport(
                                                        serviceReport
                                                    )
                                                )

                                            }
                                        }


                                    }
                                )
                            }

                        }
                    }
                }
            }

        }


    }


}


@Composable
fun EmptyListUi() {
    Column(
        modifier = Modifier.padding(16.dp),
    ) {

        Text(text = "You have no reports yet")

    }
}