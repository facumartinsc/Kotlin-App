package com.example.touchmeactivity.uimodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.touchmeactivity.data.remote.RetrofitInstance
import com.example.touchmeactivity.data.model.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    val isLoading = MutableLiveData(false)
    val loginSuccess = MutableLiveData<Boolean?>()
    val errorMessage = MutableLiveData<String?>()

    fun clearError() {
        errorMessage.value = null
    }

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
        clearError() // borra el mensaje de error al tipear
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
        clearError() // borra el mensaje de error al tipear
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = RetrofitInstance.api.login(
                    LoginRequest(_email.value ?: "", _password.value ?: "")
                )

                Log.d("LoginViewModel", "Inicio de sesión correcto: ${response.user.email}")
                loginSuccess.value = true

            } catch (e: Exception) {
                if (e.message?.contains("401") == true) {
                    errorMessage.value = "Correo o contraseña incorrectos"
                } else {
                    errorMessage.value = "Ocurrió un error. Intentalo de nuevo"
                }
                loginSuccess.value = false
            } finally {
                isLoading.value = false
            }
        }
    }
}