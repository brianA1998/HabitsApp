package com.example.habitsapp.home.domain.detail.usecase

import com.example.habitsapp.home.domain.models.Habit
import com.example.habitsapp.home.domain.repository.HomeRepository

class InsertHabitUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(habit: Habit) {
        repository.insertHabit(habit)
    }
}