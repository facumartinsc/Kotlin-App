package com.example.touchmeactivity.registro.network

class MockAuthApi : AuthApi {
    override suspend fun register(email: String, password: String): Result<Unit> {
        return if (email.contains("@")) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Correo inválido"))
        }
    }
}