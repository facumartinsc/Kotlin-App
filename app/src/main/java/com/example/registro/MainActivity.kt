package com.example.registro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.registro.repository.AuthRepository
import com.example.registro.network.MockAuthApi
import com.example.registro.ui.RegisterScreen
import com.example.registro.viewmodel.RegisterViewModel
import com. example.registro.ui.theme.RegistroAppTheme
import androidx. compose. material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = RegisterViewModel(AuthRepository(MockAuthApi()))
        setContent {
            RegistroAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterScreen(viewModel)
                }
            }
        }
    }
}