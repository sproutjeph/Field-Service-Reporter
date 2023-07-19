package com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case

data class StudentUseCases(
    val addStudent: AddStudent,
    val deleteStudent: DeleteStudent,
    val getAllStudents: GetAllStudents,
    val getStudent: GetStudent,
    val updateStudent: UpdateStudent
)