package com.example.registro.repository

import com.example.registro.network.AuthApi

class AuthRepository(private val api: AuthApi) {
    suspend fun register(email: String, password: String): Result<Unit> {
        return api.register(email, password)
    }
}
