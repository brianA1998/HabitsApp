package com.example.authentication_data.di

import com.example.authentication_data.matcher.EmailMatcherImpl
import com.example.authentication_domain.matcher.EmailMatcher
import com.example.authentication_domain.repository.AuthenticationRepository
import com.example.authentication_data.repository.AuthenticationRepositoryImpl
import com.example.authentication_domain.usecase.GetUserIdUseCase
import com.example.authentication_domain.usecase.LoginUseCases
import com.example.authentication_domain.usecase.LoginWithEmailUseCase
import com.example.authentication_domain.usecase.LogoutUseCase
import com.example.authentication_domain.usecase.SignupUseCases
import com.example.authentication_domain.usecase.SignupWithEmailUseCase
import com.example.authentication_domain.usecase.ValidateEmailUseCase
import com.example.authentication_domain.usecase.ValidatePasswordUseCase
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
            signupWithEmailUseCase = SignupWithEmailUseCase(
                repository
            ),
            validateEmailUseCase = ValidateEmailUseCase(
                emailMatcher
            ),
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
            loginWithEmailUseCase = LoginWithEmailUseCase(
                repository
            ),
            validateEmailUseCase = ValidateEmailUseCase(
                emailMatcher
            ),
            validatePasswordUseCase = ValidatePasswordUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideGetUserIdUseCase(repository: AuthenticationRepository): GetUserIdUseCase {
        return GetUserIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: AuthenticationRepository): LogoutUseCase {
        return LogoutUseCase(repository)
    }
}