package com.mbah1.jephthah.fieldservicereporter.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mbah1.jephthah.fieldservicereporter.features.schedule.ScheduleEvents
import com.mbah1.jephthah.fieldservicereporter.features.schedule.ScheduleScreen
import com.mbah1.jephthah.fieldservicereporter.features.schedule.ScheduleViewModel
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.model.ScheduleModel
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.ServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report.AddEditServiceReportEvents
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report.AddEditServiceReportScreen
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report.AddEditServiceReportViewModel
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.add_edit_report.ServiceReportInputTextFieldState
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.ServiceReportEvent
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.ServiceReportItemScreen
import com.mbah1.jephthah.fieldservicereporter.features.service_report.presentation.service_reports.ServiceReportViewModel
import com.mbah1.jephthah.fieldservicereporter.features.service_timer.ServiceTimerEvents
import com.mbah1.jephthah.fieldservicereporter.features.service_timer.ServiceTimerState
import com.mbah1.jephthah.fieldservicereporter.features.service_timer.ServiceTimerViewModel
import com.mbah1.jephthah.fieldservicereporter.features.settings.data.SettingsState
import com.mbah1.jephthah.fieldservicereporter.features.settings.presentation.SettingsScreen
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel
import com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.add_edit_student.AddEditStudentScreen
import com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.intrested_ones.InterestedOnesScreen
import com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.students.StudentDetailsScreen
import com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.students.StudentInputState
import com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.students.StudentViewModel
import com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.students.StudentsEvent
import com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.students.StudentsScreen
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.model.Destination
import com.mbah1.jephthah.fieldservicereporter.ui.screens.HomeScreen
import kotlinx.coroutines.launch


@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    startDestination: String,
    widthSizeClass: WindowWidthSizeClass,
    navController: NavHostController,
    currentDestination: Destination,

) {
    val serviceReportViewModel = hiltViewModel<ServiceReportViewModel>()
    val serviceTimerViewModel = hiltViewModel<ServiceTimerViewModel>()
    val scheduleViewModel = hiltViewModel<ScheduleViewModel>()
    val serviceTimerUiState = serviceTimerViewModel.serviceTimerUiState.collectAsStateWithLifecycle().value

    val serviceReportState = serviceReportViewModel.state.value

    val addEditServiceReportViewModel = hiltViewModel<AddEditServiceReportViewModel>()

    val studentViewModel = hiltViewModel<StudentViewModel>()
    val studentState = studentViewModel.state.value
    val scheduleState = scheduleViewModel.state.value


    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        homeRoute(
            serviceReports = serviceReportState.report,
            widthSizeClass = widthSizeClass,
            handleServiceReportEvents = { events ->
                serviceReportViewModel.viewModelScope.launch {
                    serviceReportViewModel.onEvent(events)
                }
            },
            handleServiceTimerEvents = { events ->
                serviceTimerViewModel.viewModelScope.launch {
                    serviceTimerViewModel.onEvent(events)
                }
            },
            navigateToAddEditReportScreenWithArgs = {
                navController.navigate(Destination.AddEditReport.passReportId(it))
            },
            serviceTimerState = serviceTimerUiState,
            navigateToAddEditReportScreen = {
                navController.navigate(Destination.AddEditReport.path)
            }

        )

        addEditServiceReportRoute(
            navigateUp = {navController.popBackStack()},
            widthSizeClass = widthSizeClass,
            addEditServiceReportViewModel = addEditServiceReportViewModel,
            serviceReportViewModel = serviceReportViewModel
        )

        studentRoute(
            currentDestination = currentDestination,
            widthSizeClass = widthSizeClass,
            onNavigate = { destination ->
                navController.navigate(destination.path) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            navigateUp = {navController.popBackStack()},
            students = studentState.students,
            onDeleteStudent = {
                studentViewModel.onEvent(StudentsEvent.DeleteStudent(it))
            },
            navigateToStudentsDetails = {
                navController.navigate(Destination.StudentDetails.passStudentId(it))
            },
            navigateToAddEditStudent = {
                navController.navigate(Destination.AddEditStudent.path)
            }
        )

        serviceReportItemRoute(
            navigateToAddEditReportScreenWithArgs =  {
                navController.navigate(Destination.AddEditReport.passReportId(it))
            },
            navigateToAddEditReportScreen = {
                navController.navigate(Destination.AddEditReport.path)
            },
            widthSizeClass = widthSizeClass,
            currentDestination = currentDestination,
            onNavigate = { destination ->
                navController.navigate(destination.path) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            serviceReports = serviceReportState.report,
            handleServiceReportEvents = { events ->
                serviceReportViewModel.viewModelScope.launch {
                    serviceReportViewModel.onEvent(events)
                }
            },
            navigateUp = {
                navController.popBackStack()
            },
        )

        interestedPersonRoute(
            navigateUp = {
                navController.popBackStack()
            },
            widthSizeClass = widthSizeClass,
            onNavigate = { destination ->
                navController.navigate(destination.path) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            currentDestination = currentDestination,
        )

        scheduleRoute(
            navigateUp = { navController.popBackStack() },
            widthSizeClass = widthSizeClass,
            onNavigate = { destination ->
                navController.navigate(destination.path) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            currentDestination = currentDestination,
            onDeleteSchedule = {
                scheduleViewModel.onEvent(ScheduleEvents.DeleteSchedule(it))
            },
            schedules = scheduleState.schedules,
            onAddSchedule = {
                scheduleViewModel.onEvent(ScheduleEvents.AddSchedule(it))

            },
            selectedScheduleDate = scheduleState.selectedScheduleDate,
            setSelectScheduleDate = {
                scheduleViewModel.onEvent(ScheduleEvents.SetSelectedScheduleDate(it))
            }
        )

        addEditStudentRoute(
            navigateUp = {
                navController.popBackStack()
            },
            widthSizeClass = widthSizeClass,

            students = studentState.students,
            studentViewModel = studentViewModel
        )

        studentDetailsRoute(
            navigateUp = {
                navController.popBackStack()
            },
            widthSizeClass = widthSizeClass,
            navigateToAddEditStudent = {
                navController.navigate(Destination.AddEditStudent.passStudentId(it))
            },
            students = studentState.students,
        )

        settingsRoute()

    }

}


fun NavGraphBuilder.homeRoute(
    serviceReports: List<ServiceReport>,
    serviceTimerState: ServiceTimerState,
    navigateToAddEditReportScreenWithArgs: (reportId: String) -> Unit,
    handleServiceTimerEvents: (events: ServiceTimerEvents) -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    navigateToAddEditReportScreen: () -> Unit,
    handleServiceReportEvents: (ServiceReportEvent) -> Unit,
){
    composable(Destination.Home.path) {
        HomeScreen(
            serviceReports = serviceReports,
            widthSizeClass = widthSizeClass,
            handleServiceTimerEvents = handleServiceTimerEvents,
            serviceTimerState = serviceTimerState,
            navigateToAddEditReportScreenWithArgs = navigateToAddEditReportScreenWithArgs,
            navigateToAddEditReportScreen = navigateToAddEditReportScreen,
            handleServiceReportEvents = handleServiceReportEvents
        )
    }
}


fun NavGraphBuilder.addEditServiceReportRoute(
    navigateUp: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    serviceReportViewModel: ServiceReportViewModel,
    addEditServiceReportViewModel: AddEditServiceReportViewModel,
){
    composable(
        route = Destination.AddEditReport.path,
         arguments = listOf(navArgument(name = "reportId") {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        })
    ) { selectedServiceReportId ->
        val reportId = selectedServiceReportId.arguments?.getString("reportId")
        val serviceReports = serviceReportViewModel.state.value.report
        val reportToEdit = serviceReports.find { it.id.toString() == reportId }
        if (reportToEdit?.id != null) {
            AddEditServiceReportScreen(
                serviceReportInputTextFieldStateState = ServiceReportInputTextFieldState(
                    id = reportToEdit.id,
                    name = remember { mutableStateOf(reportToEdit.name) },
                    month = remember { mutableStateOf(reportToEdit.month) },
                    placement = remember { mutableStateOf(reportToEdit.placement) },
                    videoShowing = remember { mutableStateOf(reportToEdit.videoShowing) },
                    hours = remember { mutableStateOf(reportToEdit.hours) },
                    returnVisits = remember { mutableStateOf(reportToEdit.returnVisits) },
                    bibleStudies = remember { mutableStateOf(reportToEdit.bibleStudies) },
                    comments = remember { mutableStateOf(reportToEdit.comments) },

                    ),
                id = reportToEdit.id,
                navigateUp = navigateUp,
                widthSizeClass = widthSizeClass,
                onAddReport = {
                    addEditServiceReportViewModel.onEvent(AddEditServiceReportEvents.OnAdd(it))
                },
                onEditReport = {
                    addEditServiceReportViewModel.onEvent(AddEditServiceReportEvents.OnUpdate(it))
                },
                showSnackbar = false,
            )

        } else {
            AddEditServiceReportScreen(
                navigateUp = navigateUp,
                widthSizeClass = widthSizeClass,
                serviceReportInputTextFieldStateState = ServiceReportInputTextFieldState(
                    name = remember { mutableStateOf("") },
                    month = remember { mutableStateOf("") },
                    placement = remember { mutableStateOf("") },
                    videoShowing = remember { mutableStateOf("") },
                    hours = remember { mutableStateOf("") },
                    returnVisits = remember { mutableStateOf("") },
                    bibleStudies = remember { mutableStateOf("") },
                    comments = remember { mutableStateOf("") },
                ),
                onAddReport = {
                    addEditServiceReportViewModel.onEvent(AddEditServiceReportEvents.OnAdd(it))

                },
                onEditReport = {
                    addEditServiceReportViewModel.onEvent(AddEditServiceReportEvents.OnUpdate(it))

                },
                showSnackbar = false,


                )
        }


    }
}


fun NavGraphBuilder.studentRoute(
    navigateUp: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    onNavigate: (destination: Destination) -> Unit,
    currentDestination: Destination,
    onDeleteStudent: (student: StudentModel) -> Unit,
    students: List<StudentModel>,
    navigateToAddEditStudent: () -> Unit,
    navigateToStudentsDetails: (studentId: String) -> Unit,
){
    composable(Destination.Students.path) {
        StudentsScreen(
            currentDestination = currentDestination,
            widthSizeClass = widthSizeClass,
            onNavigate = onNavigate,
            navigateUp = navigateUp,
            students = students,
            onDeleteStudent = onDeleteStudent,
            navigateToStudentsDetails = navigateToStudentsDetails,
            navigateToAddEditStudent = navigateToAddEditStudent

        )
    }
}




fun NavGraphBuilder.serviceReportItemRoute(
    navigateToAddEditReportScreenWithArgs: (String) -> Unit,
    navigateToAddEditReportScreen: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
    serviceReports: List<ServiceReport>,
    handleServiceReportEvents: (ServiceReportEvent) -> Unit,
    navigateUp: () -> Unit
){
    composable(Destination.Reports.path) {
        ServiceReportItemScreen(
            navigateToAddEditReportScreenWithArgs = navigateToAddEditReportScreenWithArgs,
            navigateToAddEditReportScreen = navigateToAddEditReportScreen,
            widthSizeClass = widthSizeClass,
            currentDestination = currentDestination,
            onNavigate = onNavigate,
            serviceReports = serviceReports,
            handleServiceReportEvents = handleServiceReportEvents,
            navigateUp = navigateUp
        )

    }
}


fun NavGraphBuilder.interestedPersonRoute(
    navigateUp: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {

    composable(Destination.Interests.path) {
        InterestedOnesScreen(
            navigateUp = navigateUp,
            widthSizeClass = widthSizeClass,
            currentDestination = currentDestination,
            onNavigate = onNavigate
        )
    }
}


fun NavGraphBuilder.scheduleRoute(
    navigateUp: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
    onDeleteSchedule: (schedule: ScheduleModel) -> Unit,
    onAddSchedule:(scheduleModel: ScheduleModel) -> Unit,
    schedules: List<ScheduleModel>,
    selectedScheduleDate:String,
    setSelectScheduleDate: (selectedScheduleDate: String)-> Unit
) {
    composable(Destination.Schedule.path) {
        ScheduleScreen(
            onDeleteSchedule = onDeleteSchedule,
            navigateUp = navigateUp,
            widthSizeClass = widthSizeClass,
            currentDestination = currentDestination,
            onNavigate = onNavigate,
            onAddSchedule = onAddSchedule,
            schedules = schedules,
            selectedScheduleDate = selectedScheduleDate,
            setSelectScheduleDate = setSelectScheduleDate
        )


    }
}


fun NavGraphBuilder.addEditStudentRoute(
    studentViewModel: StudentViewModel,
    navigateUp: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    students: List<StudentModel>
) {

    composable(
        route = Destination.AddEditStudent.path,
        arguments = listOf(navArgument(name = "studentId") {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        })
    ) {it-> val studentId = it.arguments?.getString("studentId")
        val studentToEdit = students.find { it.id.toString() == studentId }

        if (studentToEdit?.id != null) {

            AddEditStudentScreen(
                studentInputState = StudentInputState(
                    id = studentToEdit.id,
                    name = remember { mutableStateOf(studentToEdit.studentName) },
                    address = remember { mutableStateOf(studentToEdit.address) },
                    phoneNumber = remember { mutableStateOf(studentToEdit.phoneNumber) },
                    email = remember { mutableStateOf(studentToEdit.email) },
                    bookStudying = remember { mutableStateOf(studentToEdit.bookStudying) },
                    lesson = remember { mutableStateOf(studentToEdit.lessonUnderConsideration) },
                    timeOfVisit = remember { mutableStateOf(studentToEdit.timeOfVisit) },
                    questionToConsider = remember { mutableStateOf(studentToEdit.questionToConsider) },
                    note = remember { mutableStateOf(studentToEdit.noteAboutStudent) },

                    ),
                id = studentToEdit.id,
                addStudent = {
                    studentViewModel.onEvent(StudentsEvent.OnAdd(it))

                },
                updateStudent = {
                    studentViewModel.onEvent(StudentsEvent.OnUpdate(it))
                },
                navigateUp = navigateUp,
                widthSizeClass = widthSizeClass,
            )
        } else {

            AddEditStudentScreen(
                studentInputState = StudentInputState(
                    id = null,
                    name = remember { mutableStateOf("") },
                    address = remember { mutableStateOf("") },
                    phoneNumber = remember { mutableStateOf("") },
                    email = remember { mutableStateOf("") },
                    bookStudying = remember { mutableStateOf("") },
                    lesson = remember { mutableStateOf("") },
                    timeOfVisit = remember { mutableStateOf("") },
                    questionToConsider = remember { mutableStateOf("") },
                    note = remember { mutableStateOf("") },

                    ),
                addStudent = {
                    studentViewModel.onEvent(StudentsEvent.OnAdd(it))

                },
                updateStudent = {
                    studentViewModel.onEvent(StudentsEvent.OnUpdate(it))
                },
                navigateUp = navigateUp,
                widthSizeClass = widthSizeClass,

                )
        }


    }





}

fun NavGraphBuilder.studentDetailsRoute(
    navigateToAddEditStudent: (studentId: String) -> Unit,
    navigateUp: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    students: List<StudentModel>
) {

    composable(
        route = Destination.StudentDetails.path,
        arguments = listOf(navArgument(name = "studentId") {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        })
    ) { it ->
        val studentId = it.arguments?.getString("studentId") ?: "student"
        val studentDetails = students.find { it.id.toString() == studentId }
        StudentDetailsScreen(
            studentDetails = studentDetails,
            navigateToAddEditStudent = navigateToAddEditStudent,
            navigateUp = navigateUp,
            widthSizeClass = widthSizeClass
        )


    }

}

fun NavGraphBuilder.settingsRoute(){
    composable(
        route = Destination.Settings.path
    ){
        SettingsScreen()
    }
}




