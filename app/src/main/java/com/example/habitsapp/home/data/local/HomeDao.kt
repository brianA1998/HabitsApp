package com.example.habitsapp.home.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habitsapp.home.data.local.entity.HabitEntity
import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Delete
import com.example.habitsapp.home.data.local.entity.HabitSyncEntity

@Dao
interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habitEntity: HabitEntity)

    @Query("SELECT * FROM HabitEntity WHERE id = :id")
    suspend fun getHabitById(id: String): HabitEntity

    @Query("SELECT * FROM HabitEntity WHERE startDate <= :date ORDER BY id ASC")
    fun getAllHabitsForSelectedDate(date: Long): Flow<List<HabitEntity>>

    @Query("SELECT * FROM HabitEntity")
    fun getAllHabits(): List<HabitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitSync(habitSyncEntity: HabitSyncEntity)

    @Query("SELECT * FROM HabitSyncEntity")
    fun getAllHabitsSync(): List<HabitSyncEntity>

    @Delete
    suspend fun deleteHabitSync(habitSyncEntity: HabitSyncEntity)

}