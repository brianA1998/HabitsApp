package com.example.habitsapp.home.domain.home.usecase

data class HomeUseCases(
    val completeHabitUseCase: CompleteHabitUseCase,
    val getHabitsForDateUseCase: GetHabitsForDateUseCase,
    val syncHabitsUseCase: SyncHabitUseCase,

    )
