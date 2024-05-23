package com.example.habitsapp.home.presentation.home

import com.example.habitsapp.home.domain.models.Habit
import java.time.ZonedDateTime

sealed interface HomeEvent {
    data class ChangeDate(val date: ZonedDateTime) : HomeEvent
    data class CompleteHabit(val habit: Habit) : HomeEvent
}