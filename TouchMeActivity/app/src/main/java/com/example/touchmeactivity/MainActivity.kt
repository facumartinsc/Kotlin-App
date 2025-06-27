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
import com.example.touchmeactivity.data.api.SupabaseAuthApi
import com.example.touchmeactivity.registro.repository.AuthRepository
import com.example.touchmeactivity.registro.uitheme.RegisterScreen
import com.example.touchmeactivity.registro.viewmodel.RegisterViewModel
import com.example.touchmeactivity.ui.login.TouchMeActivityTheme
import com.example.touchmeactivity.ui.login.ui.LoginScreen
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    // Retrofit + API instancia global para la actividad
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
                var isRegister by remember { mutableStateOf(false) }
                var isLoggedIn by remember { mutableStateOf(false) }

                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    if (isLoggedIn) {
                        androidx.compose.material3.Text(
                            text = "¡Bienvenido!",
                            modifier = Modifier.padding(padding)
                        )
                    } else {
                        if (isRegister) {
                            val registerViewModel = remember {
                                RegisterViewModel(AuthRepository(supabaseAuthApi))
                            }
                            RegisterScreen(viewModel = registerViewModel)
                        } else {
                            LoginScreen(
                                modifier = Modifier.padding(padding),
                                onRegisterClick = { isRegister = true },
                                onLoginSuccess = {
                                    isLoggedIn = true
                                }
                            )
                        }
                    }
                }
            }

        }
    }
}



