package com.example.habitsapp.authentication.domain.usecase

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class ValidatePasswordUseCaseTest {

    private lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @Before
    fun setUp() {
        validatePasswordUseCase = ValidatePasswordUseCase()
    }


    @Test
    fun givenLowCharacterPasswordReturnInvalidPassword() {
        val input = "asd"
        val result = validatePasswordUseCase(input)
        assertEquals(
            PasswordResult.INVALID_LENGTH,
            result
        )
    }


    @Test
    fun givenNoLowercaseCharacterPasswordReturnInvalidPassword() {
        val input = "ASDSADADDSADA"
        val result = validatePasswordUseCase(input)
        assertEquals(
            PasswordResult.INVALID_LOWERCASE,
            result
        )
    }


    @Test
    fun givenNoUppercaseCharacterPasswordReturnInvalidPassword() {
        val input = "dsadsada"
        val result = validatePasswordUseCase(input)
        assertEquals(
            PasswordResult.INVALID_UPPERCASE,
            result
        )
    }

    @Test
    fun givenNoDigitCharacterPasswordReturnInvalidPassword() {
        val input = "Ddsadsada"
        val result = validatePasswordUseCase(input)
       assertEquals(PasswordResult.INVALID_DIGITS, result)
    }


    @Test
    fun givenValidPasswordReturnValidPassword() {
        val input = "Ddsadsada123"
        val result = validatePasswordUseCase(input)
        assertEquals(PasswordResult.VALID, result)
    }


}