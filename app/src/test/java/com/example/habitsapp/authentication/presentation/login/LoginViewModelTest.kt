package com.example.habitsapp.authentication.presentation.login

import com.example.habitsapp.authentication.data.repository.FakeAuthenticationRepository
import com.example.authentication_domain.matcher.EmailMatcher
import com.example.authentication_domain.usecase.LoginUseCases
import com.example.authentication_domain.usecase.LoginWithEmailUseCase
import com.example.authentication_domain.usecase.ValidateEmailUseCase
import com.example.authentication_domain.usecase.ValidatePasswordUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    private lateinit var loginViewModel: com.example.authentication_presentation.login.LoginViewModel
    private lateinit var authenticationRepository: FakeAuthenticationRepository

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Before
    fun setUp() {
        authenticationRepository = FakeAuthenticationRepository()
        val usecases = com.example.authentication_domain.usecase.LoginUseCases(
            loginWithEmailUseCase = com.example.authentication_domain.usecase.LoginWithEmailUseCase(
                authenticationRepository
            ),
            validatePasswordUseCase = com.example.authentication_domain.usecase.ValidatePasswordUseCase(),
            validateEmailUseCase = com.example.authentication_domain.usecase.ValidateEmailUseCase(
                object : com.example.authentication_domain.matcher.EmailMatcher {
                    override fun isValid(email: String): Boolean {
                        return email.isNotEmpty()
                    }
                })
        )
        loginViewModel =
            com.example.authentication_presentation.login.LoginViewModel(usecases, dispatcher)
    }

    @Test
    fun `inital state is empty`() {
        val state = loginViewModel.state
        assertEquals(
            com.example.authentication_presentation.login.LoginState(
                email = "",
                password = "",
                emailError = null,
                passwordError = null,
                isLoggedIn = false,
                isLoading = false
            ),
            state
        )
    }

    @Test
    fun `given an email, verify the state updates the email`() {
        val initialState = loginViewModel.state.email
        assertEquals(initialState, "")
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.EmailChange("asd@asd.com"))
        val updatedState = loginViewModel.state.email
        assertEquals(
            "asd@asd.com",
            updatedState
        )
    }

    @Test
    fun `given invalid email, show email error`() {
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.EmailChange(""))
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.Login)
        val state = loginViewModel.state
        assertNotNull(state.emailError)
    }

    @Test
    fun `set valid email, Login, no email error`() {
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.EmailChange("whatever"))
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.Login)
        val state = loginViewModel.state
        assert(state.emailError == null)
    }

    @Test
    fun `set invalid password, Login, show password error`() {
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.PasswordChange("asd"))
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.Login)
        val state = loginViewModel.state
        assertNotNull(state.passwordError)
    }

    @Test
    fun `set valid password, Login, no password error`() {
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.PasswordChange("asdASD123"))
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.Login)
        val state = loginViewModel.state
        assertNull(state.passwordError)
    }

    @Test
    fun `set valid details, Login, starts loading and then logs in`() = scope.runTest {
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.EmailChange("whatever"))
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.PasswordChange("asdASD123"))
        loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.Login)
        var state = loginViewModel.state
        assertNull(state.passwordError)
        assertNull(state.emailError)
        assert(state.isLoading)
        advanceUntilIdle()
        state = loginViewModel.state
        assert(state.isLoggedIn)
    }

    @Test
    fun `set valid details but server error, Login, starts loading and then show error`() =
        scope.runTest {
            authenticationRepository.fakeError = true
            loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.EmailChange("whatever"))
            loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.PasswordChange("asdASD123"))
            loginViewModel.onEvent(com.example.authentication_presentation.login.LoginEvent.Login)
            var state = loginViewModel.state
            assertNull(state.passwordError)
            assertNull(state.emailError)
            assert(state.isLoading)
            advanceUntilIdle()
            state = loginViewModel.state
            assert(!state.isLoggedIn)
            assertNotNull(state.emailError)
            assert(!state.isLoading)
        }
}