package com.example.authentication_presentation.util

import com.example.authentication_domain.usecase.PasswordResult

object PasswordErrorParser {
    fun passwordError(error: PasswordResult): String? {
        return when (error) {
            PasswordResult.VALID -> null
            PasswordResult.INVALID_LOWERCASE -> "La contraseña tiene que tener al menos 1 caracter en minuscula"
            PasswordResult.INVALID_UPPERCASE -> "La contraseña tiene que tener al menos 1 caracter en mayuscula"
            PasswordResult.INVALID_DIGITS -> "La contraseña tiene que tener al menos 1 numero"
            PasswordResult.INVALID_LENGTH -> "La contraseña tiene que tener al menos 8 caracteres"
        }
    }
}