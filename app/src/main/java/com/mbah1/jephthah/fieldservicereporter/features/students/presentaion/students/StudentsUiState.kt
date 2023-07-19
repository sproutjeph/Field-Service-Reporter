package com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.students

import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel


data class StudentsUiState(
    val students: List<StudentModel> = emptyList(),

    )
