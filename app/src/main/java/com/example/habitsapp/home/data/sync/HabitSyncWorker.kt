package com.example.habitsapp.home.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.habitsapp.home.data.local.HomeDao
import com.example.habitsapp.home.data.local.entity.HabitSyncEntity
import com.example.habitsapp.home.data.mapper.toDomain
import com.example.habitsapp.home.data.mapper.toDto
import com.example.habitsapp.home.data.remote.HomeApi
import com.example.habitsapp.home.data.remote.util.resultOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

@HiltWorker
class HabitSyncWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParams: WorkerParameters,
    private val api: HomeApi,
    private val dao: HomeDao
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val items = dao.getAllHabitsSync()

        if (runAttemptCount >= 3) {
            return Result.failure()
        }

        return try {
            supervisorScope {
                val jobs = items.map { items -> launch { sync(items) } }
                jobs.forEach { it.join() }
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    /**
     * Syncs a habit delete or update with the server
     */
    private suspend fun sync(entity: HabitSyncEntity) {
        val habit = dao.getHabitById(entity.id).toDomain().toDto()
        resultOf {
            api.insertHabit(habit)
        }.onSuccess {
            dao.deleteHabitSync(entity)
        }.onFailure {
            throw it
        }

    }
}