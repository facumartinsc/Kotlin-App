package com.example.touchmeactivity.data.model

data class LoginResponse(
    val access_token: String,
    val refresh_token: String,
    val expires_in: Int,
    val token_type: String,
    val user: SupabaseUser
)

data class SupabaseUser(
    val id: String,
    val email: String
)