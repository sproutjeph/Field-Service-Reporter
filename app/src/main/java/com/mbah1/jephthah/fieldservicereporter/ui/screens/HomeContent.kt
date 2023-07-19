package com.mbah1.jephthah.fieldservicereporter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.components.ServiceReportItem
import com.mbah1.jephthah.fieldservicereporter.features.service_timer.ServiceTimer
import com.mbah1.jephthah.fieldservicereporter.features.service_timer.ServiceTimerEvents
import com.mbah1.jephthah.fieldservicereporter.features.service_timer.ServiceTimerState

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    handleServiceTimerEvents: (events: ServiceTimerEvents)->Unit,
    navigateToAddEditReportScreenWithArgs: (reportId: String) -> Unit,
    serviceTimerState: ServiceTimerState,
    widthSizeClass: WindowWidthSizeClass,
    onDeleteClicked: ()-> Unit,
    currentReport: ServiceReport?
    ) {

    Row(
        modifier = modifier
            .fillMaxSize()
    ) {



        if(widthSizeClass == WindowWidthSizeClass.Compact){
            PortraitView(
                serviceTimerState = serviceTimerState,
                handleServiceTimerEvents = handleServiceTimerEvents,
                onDeleteClicked = onDeleteClicked,
                navigateToAddEditReportScreenWithArgs = navigateToAddEditReportScreenWithArgs,
                currentReport = currentReport
            )
        }else{
            GridView(
                modifier = Modifier,
                serviceTimerState = serviceTimerState,
                handleServiceTimerEvents = handleServiceTimerEvents,
                onDeleteClicked = onDeleteClicked,
                navigateToAddEditReportScreenWithArgs = navigateToAddEditReportScreenWithArgs,
                currentReport = currentReport
            )
        }





    }
}

@Composable
fun PortraitView(
    modifier: Modifier = Modifier,
    serviceTimerState: ServiceTimerState,
    onDeleteClicked: ()-> Unit,
    handleServiceTimerEvents: (events: ServiceTimerEvents) -> Unit,
    navigateToAddEditReportScreenWithArgs: (reportId: String) -> Unit,
    currentReport: ServiceReport?

    ) {


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ServiceTimer(
            modifier = Modifier,
            serviceTimerState = serviceTimerState,
            handleServiceTimerEvents = handleServiceTimerEvents
        )

        LazyColumn(modifier = Modifier){
            item{
                if (currentReport != null) {
                    ServiceReportItem(
                        serviceReport = currentReport,
                        onDeleteClicked = onDeleteClicked,
                        navigateToAddEditReportScreenWithArgs =  navigateToAddEditReportScreenWithArgs
                    )
                }
            }
        }


    }

}

@Composable
fun GridView(
    modifier: Modifier = Modifier,
    serviceTimerState: ServiceTimerState,
    onDeleteClicked: ()-> Unit,
    currentReport: ServiceReport?,
    navigateToAddEditReportScreenWithArgs: (reportId: String) -> Unit,
    handleServiceTimerEvents: (events: ServiceTimerEvents) -> Unit
) {

    Row(
        modifier = modifier
    ) {

        ServiceTimer(
            modifier = Modifier
                .weight(1f),
            serviceTimerState = serviceTimerState,
            handleServiceTimerEvents = handleServiceTimerEvents

        )
        LazyColumn(modifier = Modifier.weight(1f)){
            item{
                if (currentReport != null) {
                    ServiceReportItem(
                        serviceReport = currentReport,
                        navigateToAddEditReportScreenWithArgs = navigateToAddEditReportScreenWithArgs,
                        onDeleteClicked = onDeleteClicked
                    )
                }
            }
        }






    }

}

@Preview(showBackground = true, widthDp = 600, heightDp = 640)
@Composable
fun PreviewGridView(){
    GridView(
        serviceTimerState = ServiceTimerState(),
        handleServiceTimerEvents = { /*TODO*/ },
        onDeleteClicked = {},
        navigateToAddEditReportScreenWithArgs = {},
        currentReport = null
    )
}


@Preview
@Composable
fun PreviewPortraitView() {
    PortraitView(
        serviceTimerState = ServiceTimerState(),
        handleServiceTimerEvents = { },
                onDeleteClicked = {},
        navigateToAddEditReportScreenWithArgs = {},
        currentReport = null

    )
}