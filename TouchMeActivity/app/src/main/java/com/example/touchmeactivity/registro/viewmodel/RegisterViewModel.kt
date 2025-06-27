package com.example.touchmeactivity.registro.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.touchmeactivity.registro.repository.AuthRepository
import com.example.touchmeactivity.registro.uimodel.RegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username, successMessage = null, error = null) }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, successMessage = null, error = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, successMessage = null, error = null) }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.update { it.copy(confirmPassword = confirmPassword, successMessage = null, error = null) }
    }


    fun register() {
        val state = _uiState.value

        if (state.username.isBlank()) {
            _uiState.update { it.copy(error = "El nombre de usuario es obligatorio") }
            return
        }

        if (state.username.length < 3) {
            _uiState.update { it.copy(error = "El nombre ingresado debe tener al menos 3 caracteres") }
            return
        }

        if (state.email.isBlank()) {
            _uiState.update { it.copy(error = "El correo es obligatorio") }
            return
        }

        if (!isValidEmail(state.email)) {
            _uiState.update { it.copy(error = "El correo no es válido") }
            return
        }

        if (state.password.isBlank()) {
            _uiState.update { it.copy(error = "La contraseña es obligatoria") }
            return
        }

        if (state.password.length < 6) {
            _uiState.update { it.copy(error = "La contraseña debe tener al menos 6 caracteres") }
            return
        }

        if (state.password != state.confirmPassword) {
            _uiState.update { it.copy(error = "Las contraseñas no coinciden") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, successMessage = null) }

            val result = repository.register(state.email, state.password)

            _uiState.update {
                if (result.isSuccess) {
                    it.copy(
                        isLoading = false,
                        error = null,
                        successMessage = "Registro exitoso",
                        accessToken = result.getOrNull()?.access_token
                    )
                } else {
                    it.copy(
                        isLoading = false,
                        error = result.exceptionOrNull()?.message,
                        successMessage = null
                    )
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }
}