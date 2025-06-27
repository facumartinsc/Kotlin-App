package com.example.touchmeactivity.registro.repository

import com.example.touchmeactivity.data.api.SupabaseAuthApi
import com.example.touchmeactivity.ui.login.domain.model.LoginRequest
import com.example.touchmeactivity.ui.login.domain.model.LoginResponse

class AuthRepository(private val api: SupabaseAuthApi) {
    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return runCatching {
            api.login(LoginRequest(email, password))
        }
    }

    suspend fun register(email: String, password: String): Result<LoginResponse> {
        return runCatching {
            api.register(LoginRequest(email, password))
        }
    }
}

