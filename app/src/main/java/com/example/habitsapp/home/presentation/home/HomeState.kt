package com.example.habitsapp.home.presentation.home

import com.example.habitsapp.home.domain.models.Habit
import java.time.LocalTime
import java.time.ZonedDateTime

data class HomeState(
    val currentDate: ZonedDateTime = ZonedDateTime.now(),
    val selectedDate: ZonedDateTime = ZonedDateTime.now(),
    val habits: List<Habit> = mockHabits
)


private val mockHabits = (1..30).map {
    Habit(
        id = it.toString(),
        name = "Habito $it",
        frequency = listOf(),
        completedDates = listOf(),
        reminder = LocalTime.now(),
        startDate = ZonedDateTime.now()
    )
}
