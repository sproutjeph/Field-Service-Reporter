package com.mbah1.jephthah.fieldservicereporter.features.schedule.domain.use_case

data class ScheduleUseCases(
    val getAllSchedules: GetAllSchedules,
    val updateSchedule: UpdateSchedule,
    val deleteSchedule: DeleteSchedule,
    val getSchedule: GetSchedule,
    val addSchedule: AddSchedule
)
