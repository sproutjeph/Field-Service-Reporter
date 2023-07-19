package com.mbah1.jephthah.fieldservicereporter.features.students.domain.repository

import com.mbah1.jephthah.fieldservicereporter.features.students.data.data_source.StudentDao
import com.mbah1.jephthah.fieldservicereporter.features.students.data.repository.StudentRepository
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel
import kotlinx.coroutines.flow.Flow

class StudentRepositoryImpl (
    private val studentDao: StudentDao
): StudentRepository {
    override fun getAllStudents(): Flow<List<StudentModel>> {
        return  studentDao.getAllStudents()
    }

    override suspend fun addStudent(studentModel: StudentModel) {
        return studentDao.addStudent(studentModel)
    }

    override suspend fun deleteAllStudents() {
        return studentDao.deleteAllStudents()
    }

    override suspend fun deleteStudent(studentModel: StudentModel) {
        return studentDao.deleteStudent(studentModel)
    }

    override suspend fun getStudentById(studentId: String): StudentModel {
        return studentDao.getStudentById(studentId)
    }

    override suspend fun updateStudent(studentModel: StudentModel) {
        return studentDao.updateStudent(studentModel)
    }

}