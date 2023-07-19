package com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.students

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.InvalidReportException
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case.StudentUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor (
    private  val studentUseCases: StudentUseCases
): ViewModel(){


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _state = mutableStateOf(StudentsUiState())
    val state: State<StudentsUiState> = _state

    private var getStudentsJob: Job? = null



    init {
        getStudents()
    }

    fun onEvent(event: StudentsEvent){
        when(event){
            is StudentsEvent.OnAdd ->{
                addStudent(event.studentModel)
            }
            is StudentsEvent.OnUpdate -> {
                updateStudent(event.studentModel)
            }
            is StudentsEvent.DeleteStudent -> {
                deleteStudent(event.studentModel)
            }
        }
    }



    private fun getStudents(){
        getStudentsJob?.cancel()
        getStudentsJob = studentUseCases.getAllStudents()
            .onEach {students->
                _state.value = state.value.copy(
                    students = students
                )

            }.launchIn(viewModelScope)
    }








   private fun addStudent(studentModel: StudentModel) = viewModelScope.launch {
       try {
           studentUseCases.addStudent(studentModel)
       }catch (e: InvalidReportException){
           _eventFlow.emit(
               UiEvent.ShowSnackBar(
                   message = e.message ?: "Couldn't save Studen"
               )
           )

       }
    }

    private fun updateStudent(studentModel: StudentModel) = viewModelScope.launch {
        studentUseCases.updateStudent(studentModel)
    }

   private fun deleteStudent(studentModel: StudentModel) = viewModelScope.launch {
        studentUseCases.deleteStudent(studentModel)
    }



    sealed class UiEvent{
        data class ShowSnackBar(val message: String): UiEvent()
        object AddStudent: UiEvent()
    }


}