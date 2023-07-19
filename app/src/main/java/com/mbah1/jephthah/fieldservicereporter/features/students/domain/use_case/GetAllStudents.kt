package com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case

import com.mbah1.jephthah.fieldservicereporter.features.students.data.repository.StudentRepository
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel
import kotlinx.coroutines.flow.Flow

class GetAllStudents(
    private val studentRepository: StudentRepository
) {
    operator fun invoke(): Flow<List<StudentModel>> {
        return studentRepository.getAllStudents()
    }
}