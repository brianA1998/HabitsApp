package com.example.habitsapp.home.data.repository

import com.example.habitsapp.home.data.extension.toStartOfDateTimestamp
import com.example.habitsapp.home.data.local.HomeDao
import com.example.habitsapp.home.data.mapper.toDomain
import com.example.habitsapp.home.data.mapper.toDto
import com.example.habitsapp.home.data.mapper.toEntity
import com.example.habitsapp.home.data.remote.HomeApi
import com.example.habitsapp.home.data.remote.util.resultOf
import com.example.habitsapp.home.domain.models.Habit
import com.example.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.CancellationException
import java.time.ZonedDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.lang.Exception


class HomeRepositoryImpl(
    private val dao: HomeDao,
    private val api: HomeApi
) : HomeRepository {

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        val localFlow = dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp())
            .map { it.map { it.toDomain() } }

        val apiFlow = getHabitsFromApi()


        return localFlow.combine(apiFlow) { db, _ ->
            db
        }
    }

    private fun getHabitsFromApi(): Flow<List<Habit>> {
        return flow {
            resultOf {
                val habits = api.getAllHabits().toDomain()
                insertHabits(habits)
            }
            emit(emptyList<Habit>())
        }.onStart {
            emit(emptyList())
        }
    }

    private suspend fun insertHabits(habits: List<Habit>) {
        dao.insertHabits(habits.map { it.toEntity() })
    }

    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit.toEntity())
        resultOf {
            api.insertHabit(habit.toDto())
        }
    }

    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id).toDomain()
    }
}