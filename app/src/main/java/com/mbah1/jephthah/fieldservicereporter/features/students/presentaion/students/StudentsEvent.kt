package com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.students

import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel

sealed class StudentsEvent{
    data class OnUpdate(val studentModel: StudentModel): StudentsEvent()
    data class OnAdd(val studentModel: StudentModel): StudentsEvent()
    data class DeleteStudent(val studentModel: StudentModel): StudentsEvent()
}


