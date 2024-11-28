package com.example.onboarding_domain.usecase

import com.example.onboarding_domain.repository.OnboardingRepository

class CompleteOnboardingUseCase(private val repository: OnboardingRepository) {
        operator fun invoke(){
            return repository.completeOnboarding()
        }
}