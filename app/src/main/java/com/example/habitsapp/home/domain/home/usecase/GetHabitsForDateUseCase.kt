package com.example.habitsapp.home.domain.home.usecase

import com.example.habitsapp.home.domain.models.Habit
import com.example.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

class GetHabitsForDateUseCase(
    private val repository: HomeRepository
) {
    operator fun invoke(date: ZonedDateTime): Flow<List<Habit>> {
        return repository.getAllHabitsForSelectedDate(date)
    }
}