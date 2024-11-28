package com.example.home_data.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.example.home_data.alarm.AlarmHandlerImpl
import com.example.home_data.local.HomeDao
import com.example.home_data.local.HomeDatabase
import com.example.home_data.local.typeconverter.HomeTypeConverter
import com.example.home_data.remote.HomeApi
import com.example.home_data.repository.HomeRepositoryImpl
import com.example.home_domain.alarm.AlarmHandler
import com.example.home_domain.usecase.DetailUseCases
import com.example.home_domain.usecase.GetHabitByIdUseCase
import com.example.home_domain.usecase.InsertHabitUseCase
import com.example.home_domain.usecase.CompleteHabitUseCase
import com.example.home_domain.usecase.GetHabitsForDateUseCase
import com.example.home_domain.usecase.HomeUseCases
import com.example.home_domain.usecase.SyncHabitUseCase
import com.example.home_domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Singleton
    @Provides
    fun provideHomeUseCases(repository: com.example.home_domain.repository.HomeRepository): com.example.home_domain.usecase.HomeUseCases {
        return com.example.home_domain.usecase.HomeUseCases(
            completeHabitUseCase = com.example.home_domain.usecase.CompleteHabitUseCase(repository),
            getHabitsForDateUseCase = com.example.home_domain.usecase.GetHabitsForDateUseCase(
                repository
            ),
            syncHabitsUseCase = com.example.home_domain.usecase.SyncHabitUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideDetailUseCases(repository: com.example.home_domain.repository.HomeRepository): com.example.home_domain.usecase.DetailUseCases {
        return com.example.home_domain.usecase.DetailUseCases(
            getHabitByIdUseCase = com.example.home_domain.usecase.GetHabitByIdUseCase(repository),
            insertHabitUseCase = com.example.home_domain.usecase.InsertHabitUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideHabitDao(@ApplicationContext context: Context): HomeDao {
        return Room.databaseBuilder(
            context,
            HomeDatabase::class.java,
            "habits_db"
        ).addTypeConverter(HomeTypeConverter()).build().dao
    }


    @Singleton
    @Provides
    fun provideHomeRepository(
        dao: HomeDao,
        api: HomeApi,
        alarmHandler: com.example.home_domain.alarm.AlarmHandler,
        workManager: WorkManager
    ): com.example.home_domain.repository.HomeRepository {
        return HomeRepositoryImpl(dao, api, alarmHandler,workManager)
    }

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideAlarmHandler(@ApplicationContext context: Context): com.example.home_domain.alarm.AlarmHandler {
        return AlarmHandlerImpl(context)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    @Singleton
    @Provides
    fun provideHomeApi(client: OkHttpClient): HomeApi {
        return Retrofit.Builder().baseUrl(HomeApi.BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create()).build().create(HomeApi::class.java)
    }


}