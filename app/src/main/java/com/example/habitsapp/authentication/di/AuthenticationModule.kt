package com.example.habitsapp.authentication.di

import com.example.habitsapp.authentication.data.matcher.EmailMatcherImpl
import com.example.habitsapp.authentication.data.repository.AuthenticationRepositoryImpl
import com.example.habitsapp.authentication.domain.matcher.EmailMatcher
import com.example.habitsapp.authentication.domain.repository.AuthenticationRepository
import com.example.habitsapp.authentication.domain.usecase.LoginUseCases
import com.example.habitsapp.authentication.domain.usecase.LoginWithEmailUseCase
import com.example.habitsapp.authentication.domain.usecase.SignupUseCases
import com.example.habitsapp.authentication.domain.usecase.SignupWithEmailUseCase
import com.example.habitsapp.authentication.domain.usecase.ValidateEmailUseCase
import com.example.habitsapp.authentication.domain.usecase.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {
    @Provides
    @Singleton
    fun provideAuthenticationRepository(): AuthenticationRepository {
        return AuthenticationRepositoryImpl()
    }


    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideSignupUseCases(
        repository: AuthenticationRepository,
        emailMatcher: EmailMatcher
    ): SignupUseCases {
        return SignupUseCases(
            signupWithEmailUseCase = SignupWithEmailUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: AuthenticationRepository,
        emailMatcher: EmailMatcher
    ): LoginUseCases {
        return LoginUseCases(
            loginWithEmailUseCase = LoginWithEmailUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase()
        )
    }

}