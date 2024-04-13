package com.example.habitsapp.onboarding.domain.usecase

import com.example.habitsapp.onboarding.domain.repository.OnboardingRepository

class HasSeenOnboardingUseCase(private val repository: OnboardingRepository){
    operator fun invoke(): Boolean {
        return repository.hasSeenOnboarding()
    }
}