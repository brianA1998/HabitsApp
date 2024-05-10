package com.example.habitsapp.authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email: String) : Boolean
}