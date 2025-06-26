package com.example.touchmeactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.touchmeactivity.registro.network.MockAuthApi
import com.example.touchmeactivity.registro.repository.AuthRepository
import com.example.touchmeactivity.registro.uitheme.RegisterScreen
import com.example.touchmeactivity.registro.viewmodel.RegisterViewModel
import com.example.touchmeactivity.ui.theme.TouchMeActivityTheme
import com.example.touchmeactivity.ui.theme.ui.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TouchMeActivityTheme {
                var isRegister by remember { mutableStateOf(false) }

                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    if (isRegister) {
                        val registerViewModel = remember {
                            RegisterViewModel(AuthRepository(MockAuthApi()))
                        }
                        RegisterScreen(viewModel = registerViewModel)
                    } else {
                        LoginScreen(
                            modifier = Modifier.padding(padding),
                            onRegisterClick = { isRegister = true } // ir al registro
                        )
                    }
                }
            }
        }
    }
}


