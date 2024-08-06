package com.example.habitsapp.home.data.remote

import com.example.habitsapp.home.data.remote.dto.HabitResponse
import retrofit2.http.GET
import retrofit2.http.PATCH

interface HomeApi {
    companion object {
        const val BASE_URL = "https://dailyhabits-52160-default-rtdb.firebaseio.com/"
    }

    @GET("habits.json")
    suspend fun getAllHabits() : HabitResponse

    @PATCH("habits.json")
    suspend fun insertHabit(habitDto: HabitResponse)

}