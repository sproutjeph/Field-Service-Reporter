package com.mbah1.jephthah.fieldservicereporter.features.settings.presentation.components

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Switch
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.mbah1.jephthah.fieldservicereporter.R

@Composable
fun NotificationSettingsItem(
    modifier: Modifier = Modifier,
    title:String,


) {
    val context = LocalContext.current



    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        }else mutableStateOf(true)

    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {isGranted->
            hasNotificationPermission = isGranted

        }
    )

    val notificationEnabledState = if (hasNotificationPermission){
        stringResource(id = R.string.cd_notification_enabled)
    }else{
        stringResource(id = R.string.cd_notification_disabled)
    }

    SettingsItem(modifier = modifier) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = hasNotificationPermission,
                    onValueChange = {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }

                    },
                    role = Role.Switch
                )
                .semantics {
                    stateDescription = notificationEnabledState
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .weight(1f)
            )

            Switch(
                checked = hasNotificationPermission,
                onCheckedChange = null
            )
        }
    }

}




@Preview(showBackground = true)
@Composable
fun NotificationSettingsPreview() {

    NotificationSettingsItem(
        title = "Enable Notification",

    )
}