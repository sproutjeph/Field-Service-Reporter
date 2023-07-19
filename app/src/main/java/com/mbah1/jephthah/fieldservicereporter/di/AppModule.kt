package com.mbah1.jephthah.fieldservicereporter.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import com.mbah1.jephthah.fieldservicereporter.MainActivity
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.schedule.data.ScheduleRepository
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.repository.ScheduleRepositoryImpl
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.use_case.AddSchedule
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.use_case.DeleteSchedule
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.use_case.GetAllSchedules
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.use_case.GetSchedule
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.use_case.ScheduleUseCases
import com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.use_case.UpdateSchedule
import com.mbah1.jephthah.fieldservicereporter.features.service_report.data.data_source.ServiceReportDatabase
import com.mbah1.jephthah.fieldservicereporter.features.service_report.data.repository.ServiceReportRepository
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.repository.ServiceReportRepositoryImpl
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case.AddServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case.DeleteServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case.GetAllServiceReports
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case.GetServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case.ServiceReportUseCases
import com.mbah1.jephthah.fieldservicereporter.features.service_report.domain.use_case.UpdateServiceReport
import com.mbah1.jephthah.fieldservicereporter.features.settings.receiver.NotificationReceiver
import com.mbah1.jephthah.fieldservicereporter.features.students.data.repository.StudentRepository
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.repository.StudentRepositoryImpl
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case.AddStudent
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case.DeleteStudent
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case.GetAllStudents
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case.GetStudent
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case.StudentUseCases
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.use_case.UpdateStudent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesServiceReportDatabase(@ApplicationContext context: Context): ServiceReportDatabase =
        Room.databaseBuilder(
            context,
            klass =   ServiceReportDatabase::class.java,
            name = ServiceReportDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun providesServiceReportRepository(db: ServiceReportDatabase): ServiceReportRepository {
        return ServiceReportRepositoryImpl(db.serviceReportDao)
    }

    @Provides
    @Singleton
    fun providesServiceReportUseCases(repository: ServiceReportRepository): ServiceReportUseCases {
        return ServiceReportUseCases(
            getAllServiceReports = GetAllServiceReports(repository),
            deleteServiceReport = DeleteServiceReport(repository),
            addServiceReport = AddServiceReport(repository),
            getServiceReport = GetServiceReport(repository),
            upDateServiceReport = UpdateServiceReport(repository),
        )
    }

    @Provides
    @Singleton
    fun providesStudentRepository(db: ServiceReportDatabase): StudentRepository {
        return  StudentRepositoryImpl(db.studentDao)
    }

    @Provides
    @Singleton
    fun providesStudentUseCases(repository: StudentRepository): StudentUseCases {
        return StudentUseCases(
            getAllStudents = GetAllStudents(repository),
            deleteStudent = DeleteStudent(repository),
            addStudent = AddStudent(repository),
            updateStudent = UpdateStudent(repository),
            getStudent = GetStudent(repository)
        )
    }

    @Provides
    @Singleton
    fun providesScheduleRepository(db: ServiceReportDatabase): ScheduleRepository {
        return ScheduleRepositoryImpl(db.scheduleDao)
    }

    @Provides
    @Singleton
    fun providesScheduleUseCases(repository: ScheduleRepository): ScheduleUseCases {
        return ScheduleUseCases(
            getAllSchedules = GetAllSchedules(repository),
            deleteSchedule = DeleteSchedule(repository),
            addSchedule = AddSchedule(repository),
            updateSchedule = UpdateSchedule(repository),
            getSchedule = GetSchedule(repository)
        )
    }


    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder{
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("MESSAGE", "Clicked!")
        }

        val flag = PendingIntent.FLAG_IMMUTABLE

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            flag
        )

        val clickIntent = Intent(context, MainActivity::class.java)
        val clickPendingIntent = PendingIntent.getActivity(
            context, 1, clickIntent, flag
        )

        return NotificationCompat.Builder(context, "Main Channel ID")
            .setContentTitle("Field service Day")
            .setContentText("Remember to record your field service report for the day")
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .addAction(0,"Add Report", pendingIntent )
            .setContentIntent(clickPendingIntent)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat {

        val notificationManager = NotificationManagerCompat.from(context)
        val channel = NotificationChannel(
            "Main Channel ID",
            "Main Channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
        return notificationManager

    }

}














