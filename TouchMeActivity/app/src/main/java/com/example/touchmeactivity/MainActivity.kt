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

// 👇 Se agregó este import para usar la pantalla de juego
import com.example.touchmeactivity.screens.tocame.TocameScreen

import com.example.touchmeactivity.data.api.SupabaseAuthApi
import com.example.touchmeactivity.registro.repository.AuthRepository
import com.example.touchmeactivity.screens.RegisterScreen
import com.example.touchmeactivity.screens.LoginScreen
import com.example.touchmeactivity.uimodel.RegisterViewModel
import com.example.touchmeactivity.ui.theme.TouchMeActivityTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    // Retrofit configurado (sin cambios)
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://lvmybcyhrbisfjouhbrx.supabase.co/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val supabaseAuthApi: SupabaseAuthApi = retrofit.create(SupabaseAuthApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TouchMeActivityTheme {
                // 👇 Estados para navegación interna (sin cambios)
                var isRegister by remember { mutableStateOf(false) }
                var isLoggedIn by remember { mutableStateOf(false) }

                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    // 👇 Se reemplazó la lógica de if/else por un "when" más claro
                    when {
                        // ✅ Si el usuario está logueado, mostramos la pantalla del juego
                        isLoggedIn -> {
                            TocameScreen() // ← INTEGRACIÓN DE TOCAMESCREEN
                        }

                        // ✅ Si el usuario elige registrarse
                        isRegister -> {
                            val registerViewModel = remember {
                                RegisterViewModel(AuthRepository(supabaseAuthApi))
                            }
                            RegisterScreen(viewModel = registerViewModel)
                        }

                        // ✅ Por defecto, mostrar pantalla de login
                        else -> {
                            LoginScreen(
                                modifier = Modifier.padding(padding),
                                onRegisterClick = { isRegister = true },
                                onLoginSuccess = { isLoggedIn = true } // Al loguearse, activa el juego
                            )
                        }
                    }
                }
            }
        }
    }
}




