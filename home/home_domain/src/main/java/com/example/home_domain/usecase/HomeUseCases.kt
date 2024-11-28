package com.example.home_domain.usecase

data class HomeUseCases(
    val completeHabitUseCase: CompleteHabitUseCase,
    val getHabitsForDateUseCase: GetHabitsForDateUseCase,
    val syncHabitsUseCase: SyncHabitUseCase,

    )
