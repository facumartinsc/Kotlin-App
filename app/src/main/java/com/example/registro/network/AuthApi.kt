package com.example.registro.network

interface AuthApi {
    suspend fun register(email: String, password: String): Result<Unit>
}

