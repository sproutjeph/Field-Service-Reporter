package com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.intrested_ones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.ui.components.ServiceReportFAB
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.BottomNavigationBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.RailNavigationBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.ServiceReportTopAppBar
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterestedOnesScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.working)
    )


    Scaffold (
        modifier = modifier,
        floatingActionButton = {
            ServiceReportFAB(
                icon = Icons.Default.Add,
                onClicked = {


                }
            )
        }
    ){ contentPadding->
        if(widthSizeClass == WindowWidthSizeClass.Compact) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                LottieAnimation(composition = composition, modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                )

                Text(
                    text = "Coming Soon",
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                )

            }


        }else{
            Row(
                modifier = modifier
                    .fillMaxSize()
            ) {
                RailNavigationBar(
                    modifier = Modifier.padding(top = contentPadding.calculateTopPadding()),
                    currentDestination = currentDestination,
                    onNavigate = onNavigate,
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    LottieAnimation(composition = composition, modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                    )

                    Text(
                        text = "Coming Soon",
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp)
                    )

                }
            }
        }


    }

}