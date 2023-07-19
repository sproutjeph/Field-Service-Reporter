package com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case

import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.model.InvalidReportException
import com.mbah1.jephthah.fieldservicereporter.features.students.data.repository.StudentRepository
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel

class AddStudent(
    private val studentRepository: StudentRepository
) {
    @Throws(Exception::class, InvalidReportException::class)
    suspend operator fun invoke(studentModel: StudentModel) {
        if(studentModel.studentName.isBlank()){
            throw InvalidReportException("The name of Student can't be empty")
        }
        studentRepository.addStudent(studentModel)
    }
}