package com.mbah1.jephthah.fieldservicereporter.ui.navigation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Support
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val path: String,
    val icon: ImageVector? = null,
    val isRootDestination: Boolean = false
){
    companion object{
        fun fromString(route: String?): Destination {
            return when(route){
                Home.path -> Home
                Students.path -> Students
                Reports.path -> Reports
                Interests.path -> Interests
                Schedule.path -> Schedule
                AddEditReport.path -> Reports
                AddEditStudent.path -> Students
                StudentDetails.path -> Students
                Settings.path -> Settings
                else -> Home
            }
        }
    }

    val title = path.replaceFirstChar {
        it.uppercase()
    }

    object Home: Destination(
        "home",
        Icons.Filled.Home,
        isRootDestination = true
    )

    object Students: Destination(
        "students",
        Icons.Filled.AccountBox
    )

    object Reports: Destination(
        "reports",
        Icons.Filled.Book
    )

    object Interests: Destination(
        "interests",
        Icons.Filled.Person
    )

    object Schedule: Destination(
        "schedule",
        Icons.Filled.Schedule
    )

    object AddEditReport : Destination("add-edit-report?reportId={reportId}") {
        fun passReportId(reportId: String): String {
            return "add-edit-report?reportId=$reportId"
        }

    }

    object Settings: Destination("settings",
        Icons.Filled.Settings,
    )

    object Backup: Destination(
        "backup",
        Icons.Filled.CloudUpload,
    )
    object Help: Destination(
        "help",
        Icons.Filled.Help,
    )

    object SupportDevelopment: Destination(
        "support-development",
        Icons.Filled.Support,
    )
    object SendFeedback: Destination(
        "send-feedback",
        Icons.Filled.Email,
    )
    object PrivacyPolicy: Destination(
        "privacy-policy",
        Icons.Filled.Security,
    )

    object Creation : Destination(
        "creation",
    )

    object Add : Destination(
        "add",
        Icons.Default.Add,
    )

    object AddEditStudent : Destination("add-edit-student?studentId={studentId}") {
        fun passStudentId(studentId: String): String {
            return "add-edit-student?studentId=$studentId"
        }
    }

    object StudentDetails : Destination("student-details?studentId={studentId}") {
        fun passStudentId(studentId: String): String {
            return "student-details?studentId=$studentId"
        }
    }





}
