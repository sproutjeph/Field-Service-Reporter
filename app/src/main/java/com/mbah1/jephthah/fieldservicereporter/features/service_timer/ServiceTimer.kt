package com.mbah1.jephthah.fieldservicereporter.features.service_timer

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mbah1.jephthah.fieldservicereporter.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ServiceTimer(
    modifier: Modifier = Modifier,
    serviceTimerState: ServiceTimerState,
    handleServiceTimerEvents: (events: ServiceTimerEvents)->Unit,
    ){
    Card(
        modifier = modifier
            .height(150.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp)



    ) {

        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = stringResource(id = R.string.service_timer),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )


            Row(modifier = Modifier,

                ) {
                AnimatedContent(targetState = serviceTimerState.hours,
                    transitionSpec = { addAnimation() }) {
                    Text(
                        text = "$it : ",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                AnimatedContent(targetState =serviceTimerState.minutes,
                    transitionSpec = { addAnimation() }) {
                    Text(
                        text = "$it : ",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                AnimatedContent(targetState = serviceTimerState.seconds,
                    transitionSpec = { addAnimation() }) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        color = if(serviceTimerState.seconds == "00") MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.primary
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly

            ){
                Button(
                    onClick = { handleServiceTimerEvents(ServiceTimerEvents.OnStop) },
                    shape = RoundedCornerShape(5.dp),
                    enabled = serviceTimerState.isTimerRunning


                ) {
                    Text(text = stringResource(id = R.string.button_stop))

                }
                Button(
                    onClick = { handleServiceTimerEvents(ServiceTimerEvents.OnPause) },
                    shape = RoundedCornerShape(5.dp),
                    enabled = serviceTimerState.isTimerRunning


                ) {
                    Text(text = stringResource(id = R.string.button_pause))
                }

                Button(
                    onClick = { handleServiceTimerEvents(ServiceTimerEvents.OnStart)},
                    shape = RoundedCornerShape(5.dp),
                    enabled = !serviceTimerState.isTimerRunning
                ) {
                    Text(text = stringResource(id = R.string.button_play))
                }
            }

        }

    }
}


@ExperimentalAnimationApi
fun addAnimation(duration: Int = 600): ContentTransform {
    return (slideInVertically(animationSpec = tween(durationMillis = duration)) {
            height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    )).togetherWith(slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    ))
}