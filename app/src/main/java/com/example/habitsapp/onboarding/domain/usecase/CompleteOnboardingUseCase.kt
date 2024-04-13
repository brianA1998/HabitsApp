package com.example.habitsapp.onboarding.domain.usecase

import com.example.habitsapp.onboarding.domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(private val repository: OnboardingRepository) {
        operator fun invoke(){
            return repository.completeOnboarding()
        }
}