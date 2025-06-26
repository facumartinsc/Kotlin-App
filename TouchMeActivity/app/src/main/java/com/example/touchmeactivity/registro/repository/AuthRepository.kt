package com.example.touchmeactivity.registro.repository

import com.example.touchmeactivity.registro.network.AuthApi

class AuthRepository(private val api: AuthApi) {
    suspend fun register(email: String, password: String): Result<Unit> {
        return api.register(email, password)
    }
}
