package com.mbah1.jephthah.fieldservicereporter.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContactMail
import androidx.compose.material.icons.outlined.Copyright
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination
import kotlinx.coroutines.launch

@Composable
fun HomeNavigationDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    onNavigationSelected: (destination: Destination) -> Unit,
    navigateToAddEditReport: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier,
                content = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
                    )
                    LazyColumn{
                        item {
                            NavigationDrawerItem(
                                label = {
                                    Destination.Help.icon?.let {
                                        DrawerItem(icon = it, title = Destination.Help.title) }
                                        },
                                selected = true,
                                onClick = { onNavigationSelected(Destination.Help) }
                            )
                        }
                        item {
                            NavigationDrawerItem(
                                label = {
                                    Destination.SupportDevelopment.icon?.let {
                                        DrawerItem(icon = it, title = Destination.SupportDevelopment.title) }
                                        },
                                selected = false,
                                onClick = { onNavigationSelected(Destination.SupportDevelopment) }
                            )
                        }
                        item {
                            NavigationDrawerItem(
                                label = { Destination.SendFeedback.icon?.let {
                                    DrawerItem(icon = it, title = Destination.SendFeedback.title) } },
                                selected = false,
                                onClick = {
                                    launchSocialActivity(
                                        context = context,
                                        "mailto:"
                                    )
                                }
                            )
                        }
                        item {
                            NavigationDrawerItem(
                                label = {
                                    Destination.PrivacyPolicy.icon?.let {
                                        DrawerItem(icon = it, title = Destination.PrivacyPolicy.title) }
                                        },
                                selected = false,
                                onClick = { onNavigationSelected(Destination.PrivacyPolicy) }
                            )
                        }
                        item { Divider(thickness = 0.3.dp) }
                        item { Spacer( modifier= Modifier.padding(top = 8.dp)) }
                        item {
                            NavigationDrawerItem(
                                label = {
                                    Destination.Backup.icon?.let {
                                        DrawerItem(icon = it, title = Destination.Backup.title) }
                                        },
                                selected = false,
                                onClick = { onNavigationSelected(Destination.Backup) }
                            )
                        }
                        item {
                            NavigationDrawerItem(
                                label = {
                                    Destination.Settings.icon?.let {
                                        DrawerItem(icon = it, title = Destination.Settings.title)
                                    }
                                        },
                                selected = false,
                                onClick = {
                                    navigateToSettingsScreen()
                                    scope.launch {
                                        drawerState.close()
                                    }

                                }
                            )
                        }
                        item { Spacer( modifier= Modifier.padding(top = 8.dp)) }
                        item { Divider(thickness = 0.3.dp) }
                        item { Divider(thickness = 0.3.dp) }
                        item { Spacer( modifier= Modifier.padding(top = 8.dp)) }
                        item {
                            NavigationDrawerItem(
                                label = { DrawerItem(icon = Icons.Outlined.ContactMail, title = "jephthah.mbah@outlook.com") },
                                selected = false,
                                onClick = {/*Email Intent*/}
                            )
                        }

                        item {
                            NavigationDrawerItem(
                                label = { DrawerItem(icon = Icons.Outlined.Phone, title = "+2347065406165") },
                                selected = false,
                                onClick = { launchSocialActivity(
                                    context = context,
                                    "tel:"
                                ) }
                            )
                        }

                        item {
                            NavigationDrawerItem(
                                label = {
                                    DrawerItemSocial(
                                        icon = painterResource(id = R.drawable.ic_twitter),
                                        title = stringResource(id = R.string.reach_twitter)
                                    )
                                },
                                selected = false,
                                onClick = {
                                    launchSocialActivity(context, "twitter")
                                }
                            )
                        }

                        item {
                            NavigationDrawerItem(
                                label = {
                                    DrawerItemSocial(
                                        icon = painterResource(id = R.drawable.ic_linkedin_brands),
                                        title = stringResource(id = R.string.reach_linkedin)
                                    )
                                },
                                selected = false,
                                onClick = {
                                    launchSocialActivity(context, "linkedin")

                                }
                            )
                        }

                        item {
                            NavigationDrawerItem(
                                label = { DrawerItem(
                                    icon = Icons.Outlined.Copyright,
                                    title = stringResource(id = R.string.copy_right))
                                },
                                selected = false,
                                onClick = { /*TODO*/ }
                            )
                        }
                        item { Spacer( modifier= Modifier.padding(top = 8.dp)) }
                        item { Divider(thickness = 0.3.dp) }
                        item { Spacer( modifier= Modifier.padding(top = 24.dp)) }
                        item {
                            NavigationDrawerItem(
                                label = {
                                    Button(
                                        onClick =  {
                                            navigateToAddEditReport()
                                            scope.launch {
                                                drawerState.close()
                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(text = "Add Report")

                                    }
                                },
                                selected = false,
                                onClick = {  }
                            )
                        }
                        item { Spacer( modifier= Modifier.padding(vertical = 8.dp)) }


                    }

                }
            )
        },
        content = content
    )



}


@Composable
fun DrawerItem(
    icon: ImageVector,
    title: String,
    msgCount: String = "",

) {

    Row {
        Icon(
            imageVector = icon,
            modifier = Modifier.padding(16.dp),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp),
            text = title,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )

        if (msgCount.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp),
                text = msgCount,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )
        }

    }

}

@Composable
fun DrawerItemSocial(
    icon: Painter,
    title: String,
    msgCount: String = ""
) {

    Row {
        Icon(
            painter = icon,
            modifier = Modifier.padding(16.dp),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp),
            text = title,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            textAlign = TextAlign.Start
        )

        if (msgCount.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp),
                text = msgCount,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start
            )
        }

    }

}