package com.example.habitsapp.home.domain.detail.usecase

import com.example.habitsapp.home.domain.models.Habit
import com.example.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetHabitByIdUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(id: String): Habit {
        return withContext(Dispatchers.IO){
            repository.getHabitById(id)
        }
    }
}