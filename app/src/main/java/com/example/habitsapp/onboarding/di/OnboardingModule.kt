package com.example.habitsapp.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import com.example.habitsapp.onboarding.data.repository.OnboardingRepositoryImpl
import com.example.habitsapp.onboarding.domain.repository.OnboardingRepository
import com.example.habitsapp.onboarding.domain.usecase.CompleteOnboardingUseCase
import com.example.habitsapp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OnboardingModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences{
        return context.getSharedPreferences("habits_onboarding_preferece",Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(sharedPreferences: SharedPreferences): OnboardingRepository{
        return OnboardingRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideHasSeenOnboardingUseCase(repository: OnboardingRepository) : HasSeenOnboardingUseCase{
        return HasSeenOnboardingUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideCompleteOnboardingUseCase(repository: OnboardingRepository) : CompleteOnboardingUseCase{
        return CompleteOnboardingUseCase(repository)
    }
}