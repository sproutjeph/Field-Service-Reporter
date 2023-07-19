package com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case

import com.mbah1.jephthah.fieldservicereporter.features.students.data.repository.StudentRepository
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel

class UpdateStudent(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(studentModel: StudentModel) {
        studentRepository.updateStudent(studentModel)
    }
}