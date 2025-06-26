package com.example.touchmeactivity.registro.network

interface AuthApi {
    suspend fun register(email: String, password: String): Result<Unit>
}