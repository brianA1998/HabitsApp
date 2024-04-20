package com.example.habitsapp.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChange -> {
                state = state.copy(
                    email = event.email
                )
            }

            LoginEvent.Login -> {
                println("entre al login")
                login()
            }

            is LoginEvent.PasswordChange -> {
                state = state.copy(
                    password = event.password
                )
            }

            LoginEvent.SignUp -> TODO()
        }
    }


    private fun login() {

    }
}