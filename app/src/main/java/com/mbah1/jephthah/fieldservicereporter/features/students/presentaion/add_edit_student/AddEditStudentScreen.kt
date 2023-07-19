package com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.add_edit_student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report.components.ServiceReportInputField
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel
import com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.students.StudentInputState
import com.mbah1.jephthah.fieldservicereporter.ui.components.ServiceReportFAB
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.ServiceReportTopAppBar
import kotlinx.coroutines.launch
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditStudentScreen(
    studentInputState: StudentInputState,
    id: UUID? = null,
    updateStudent: (studentModel: StudentModel) -> Unit,
    addStudent: (studentModel: StudentModel) -> Unit,
    navigateUp: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    ) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }



    Scaffold(

        bottomBar = {},
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            ServiceReportFAB(icon = Icons.Default.Done) {
                if (id != null) {

                    updateStudent(
                        StudentModel(
                            id = id,
                            studentName = studentInputState.name.value,
                            address = studentInputState.address.value,
                            phoneNumber = studentInputState.phoneNumber.value,
                            email = studentInputState.email.value,
                            bookStudying = studentInputState.bookStudying.value,
                            lessonUnderConsideration = studentInputState.lesson.value,
                            timeOfVisit = studentInputState.timeOfVisit.value,
                            questionToConsider = studentInputState.questionToConsider.value,
                            noteAboutStudent = studentInputState.note.value
                        )
                    )
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Student Updated View Student?",
                            actionLabel = "Yes",
                            withDismissAction = true
                        )

                        if (result == SnackbarResult.ActionPerformed) {
                            navigateUp()
                        }

                    }

                } else if(
                    studentInputState.name.value.isBlank()
                ){
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Name is required",
                            withDismissAction = true
                        )
                    }

                } else {
                    addStudent(
                        StudentModel(
                            studentName = studentInputState.name.value,
                            address = studentInputState.address.value,
                            phoneNumber = studentInputState.phoneNumber.value,
                            email = studentInputState.email.value,
                            bookStudying = studentInputState.bookStudying.value,
                            lessonUnderConsideration = studentInputState.lesson.value,
                            timeOfVisit = studentInputState.timeOfVisit.value,
                            questionToConsider = studentInputState.questionToConsider.value,
                            noteAboutStudent = studentInputState.note.value
                        )
                    )
                    navigateUp()


                }

                studentInputState.name.value = ""
                studentInputState.address.value = ""
                studentInputState.phoneNumber.value = ""
                studentInputState.email.value = ""
                studentInputState.bookStudying.value = ""
                studentInputState.lesson.value = ""
                studentInputState.timeOfVisit.value = ""
                studentInputState.questionToConsider.value = ""
                studentInputState.note.value = ""


            }


        }

    ) { contentPadding ->
        if (widthSizeClass == WindowWidthSizeClass.Compact) {
            PortraitView(
                contentPadding = contentPadding,
                studentInputState = studentInputState
            )
        } else {
            GridView(
                contentPadding = contentPadding,
                studentInputState = studentInputState
            )

        }


    }

}

@Composable
fun PortraitView(
    contentPadding: PaddingValues,
    studentInputState: StudentInputState,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            ServiceReportInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                label = R.string.students_name,
                value = studentInputState.name.value,
                onValueChange = {
                    if (it.all { char ->
                            char.isDefined() || char.isWhitespace()
                        }) studentInputState.name.value = it

                },
                maxLine = 10,
                keyboardType = KeyboardType.Text


            )

            ServiceReportInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                label = R.string.address,
                value = studentInputState.address.value,
                onValueChange = {
                    if (it.all { char ->
                            char.isDefined() || char.isWhitespace()
                        }) studentInputState.address.value = it

                },
                maxLine = 10,
                keyboardType = KeyboardType.Ascii

            )
            ServiceReportInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                label = R.string.phone_number,
                value = studentInputState.phoneNumber.value,
                onValueChange = {
                    if (it.all { char ->
                            char.isDefined() || char.isWhitespace()
                        }) studentInputState.phoneNumber.value = it

                },
                maxLine = 10,
                keyboardType = KeyboardType.Phone


            )
            ServiceReportInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                label = R.string.students_email,
                value = studentInputState.email.value,
                onValueChange = {
                    if (it.all { char ->
                            char.isDefined() || char.isWhitespace()
                        }) studentInputState.email.value = it

                },
                maxLine = 10,
                keyboardType = KeyboardType.Email,


                )
            ServiceReportInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                label = R.string.students_book,
                value = studentInputState.bookStudying.value,
                onValueChange = {
                    if (it.all { char ->
                            char.isDefined() || char.isWhitespace()
                        }) studentInputState.bookStudying.value = it

                },
                maxLine = 10,
                keyboardType = KeyboardType.Text


            )
            ServiceReportInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                label = R.string.lesson,
                value = studentInputState.lesson.value,
                onValueChange = {
                    if (it.all { char ->
                            char.isDefined() || char.isWhitespace()
                        }) studentInputState.lesson.value = it

                },
                maxLine = 10,
                keyboardType = KeyboardType.Text


            )
            ServiceReportInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                label = R.string.time_of_visit,
                value = studentInputState.timeOfVisit.value,
                onValueChange = {
                    if (it.all { char ->
                            char.isDefined() || char.isWhitespace()
                        }) studentInputState.timeOfVisit.value = it

                },
                maxLine = 10,
                keyboardType = KeyboardType.Text

            )

            ServiceReportInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                label = R.string.question,
                value = studentInputState.questionToConsider.value,
                onValueChange = {
                    if (it.all { char ->
                            char.isDefined() || char.isWhitespace()
                        }) studentInputState.questionToConsider.value = it

                },
                maxLine = 10,
                keyboardType = KeyboardType.Text

            )

            ServiceReportInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                label = R.string.notes,
                value = studentInputState.note.value,
                onValueChange = {
                    if (it.all { char ->
                            char.isDefined() || char.isWhitespace()
                        }) studentInputState.note.value = it

                },
                maxLine = 10,
                keyboardType = KeyboardType.Text

            )

            // give space because of phone buttons
            Spacer(modifier = Modifier.height(250.dp))
        }


    }
}

@Composable
fun GridView(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    studentInputState: StudentInputState,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ServiceReportInputField(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    label = R.string.students_name,
                    value = studentInputState.name.value,
                    onValueChange = {
                        if (it.all { char ->
                                char.isDefined() || char.isWhitespace()
                            }) studentInputState.name.value = it

                    },
                    maxLine = 10,
                    keyboardType = KeyboardType.Text


                )

                ServiceReportInputField(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    label = R.string.address,
                    value = studentInputState.address.value,
                    onValueChange = {
                        if (it.all { char ->
                                char.isDefined() || char.isWhitespace()
                            }) studentInputState.address.value = it

                    },
                    maxLine = 10,
                    keyboardType = KeyboardType.Ascii

                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ServiceReportInputField(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    label = R.string.phone_number,
                    value = studentInputState.phoneNumber.value,
                    onValueChange = {
                        if (it.all { char ->
                                char.isDefined() || char.isWhitespace()
                            }) studentInputState.phoneNumber.value = it

                    },
                    maxLine = 10,
                    keyboardType = KeyboardType.Phone


                )
                ServiceReportInputField(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    label = R.string.students_email,
                    value = studentInputState.email.value,
                    onValueChange = {
                        if (it.all { char ->
                                char.isDefined() || char.isWhitespace()
                            }) studentInputState.email.value = it

                    },
                    maxLine = 10,
                    keyboardType = KeyboardType.Email,


                    )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ServiceReportInputField(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    label = R.string.students_book,
                    value = studentInputState.bookStudying.value,
                    onValueChange = {
                        if (it.all { char ->
                                char.isDefined() || char.isWhitespace()
                            }) studentInputState.bookStudying.value = it

                    },
                    maxLine = 10,
                    keyboardType = KeyboardType.Text


                )
                ServiceReportInputField(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    label = R.string.lesson,
                    value = studentInputState.lesson.value,
                    onValueChange = {
                        if (it.all { char ->
                                char.isDefined() || char.isWhitespace()
                            }) studentInputState.lesson.value = it

                    },
                    maxLine = 10,
                    keyboardType = KeyboardType.Text


                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ServiceReportInputField(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    label = R.string.time_of_visit,
                    value = studentInputState.timeOfVisit.value,
                    onValueChange = {
                        if (it.all { char ->
                                char.isDefined() || char.isWhitespace()
                            }) studentInputState.timeOfVisit.value = it

                    },
                    maxLine = 10,
                    keyboardType = KeyboardType.Text

                )

                ServiceReportInputField(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    label = R.string.question,
                    value = studentInputState.questionToConsider.value,
                    onValueChange = {
                        if (it.all { char ->
                                char.isDefined() || char.isWhitespace()
                            }) studentInputState.questionToConsider.value = it

                    },
                    maxLine = 10,
                    keyboardType = KeyboardType.Text

                )
            }

            Row {
                ServiceReportInputField(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    label = R.string.notes,
                    value = studentInputState.note.value,
                    onValueChange = {
                        if (it.all { char ->
                                char.isDefined() || char.isWhitespace()
                            }) studentInputState.note.value = it

                    },
                    maxLine = 10,
                    keyboardType = KeyboardType.Text

                )

                ServiceReportInputField(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    label = R.string.empty,
                    enabled = false,
                    value = "",
                    onValueChange = {},
                )
            }


            // give space because of phone buttons
            Spacer(modifier = Modifier.height(250.dp))
        }


    }


}






