package com.example.touchmeactivity.uimodel

data class RegisterUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null,
    val accessToken: String? = null
)
