package com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report.components

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ServiceReportInputField(
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    value:String = "",
    onValueChange:(String)->Unit = {},
    onImeAction: () -> Unit = {},
    maxLine: Int = 1,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Number,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true
){
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        enabled = enabled,
        maxLines = maxLine,
        colors = TextFieldDefaults.colors(),

        label = {
            Text(
                text = stringResource(id = label),
                style = MaterialTheme.typography.bodyMedium


            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(

            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }
        ),
        modifier = modifier,
        readOnly = readOnly,
        trailingIcon = trailingIcon

    )


}