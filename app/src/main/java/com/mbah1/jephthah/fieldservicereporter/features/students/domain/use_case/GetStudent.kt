package com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case

import com.mbah1.jephthah.fieldservicereporter.features.students.data.repository.StudentRepository

class GetStudent(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(studentId: String) {
        studentRepository.getStudentById(studentId)
    }
}