package com.example.habitsapp.authentication.data.repository

import com.example.habitsapp.authentication.domain.repository.AuthenticationRepository

class FakeAuthenticationRepository : AuthenticationRepository {

    var fakeError = false
    val fakeErrorMessage = "There was a server error!"

    override suspend fun login(email: String, password: String): Result<Unit> {
        return if (fakeError) {
            Result.failure(Exception(fakeErrorMessage))
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun signup(email: String, password: String): Result<Unit> {
        return if (fakeError) {
            Result.failure(Exception())
        } else {
            Result.success(Unit)
        }
    }

    override fun getUserId(): String? {
        return if (fakeError) {
            null
        } else {
            "USER_ID"
        }
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

}