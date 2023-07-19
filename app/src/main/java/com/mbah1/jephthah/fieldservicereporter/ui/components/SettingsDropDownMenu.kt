package com.mbah1.jephthah.fieldservicereporter.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsDropDownMenu(
    showDropDownMenu: MutableState<Boolean>,
){
    var expanded by remember { mutableStateOf(true) }
    val dropDownMenuItems = listOf("Settings", "Profile", "About")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 10.dp, right = 0.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                showDropDownMenu.value = false
            },
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(12.dp),



            ) {


            dropDownMenuItems.forEachIndexed{
                    _, item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item, fontSize = 17.sp)
                    },

                    onClick = {
                        expanded = false
                        showDropDownMenu.value = false
                    },
                    modifier = Modifier,
                    leadingIcon = {
                        Icon(
                            imageVector =
                            when(item){
                                "Settings" -> Icons.Default.Settings
                                "Profile" -> Icons.Default.AccountCircle
                                else -> Icons.Default.Info
                            },
                            contentDescription = "Settings icon"
                        )
                    }
                )
            }

        }

    }

}