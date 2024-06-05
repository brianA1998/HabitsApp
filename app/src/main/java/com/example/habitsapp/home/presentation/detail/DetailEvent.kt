package com.example.habitsapp.home.presentation.detail

import java.time.DayOfWeek
import java.time.LocalTime

sealed interface DetailEvent {
    data class ReminderChange(val time: LocalTime) : DetailEvent
    data class FrecuencyChange(val dayOfWeek: DayOfWeek) : DetailEvent
    data class NameChange(val name: String) : DetailEvent
    object HabitSave : DetailEvent
}