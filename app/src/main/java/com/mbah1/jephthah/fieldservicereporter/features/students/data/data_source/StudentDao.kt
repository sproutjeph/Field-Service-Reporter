package com.mbah1.jephthah.fieldservicereporter.features.students.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Query("SELECT * from student_tbl")
    fun getAllStudents(): Flow<List<StudentModel>>

    @Query("SELECT * from student_tbl where id =:id")
    suspend fun getStudentById(id:String): StudentModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStudent(studentModel: StudentModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStudent(studentModel: StudentModel)

    @Query("DELETE from student_tbl")
    suspend  fun deleteAllStudents()

    @Delete
    suspend fun deleteStudent(studentModel: StudentModel)
}