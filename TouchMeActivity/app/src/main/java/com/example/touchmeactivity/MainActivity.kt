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
import com.example.homeapp.AppNavHost
import com.example.touchmeactivity.data.api.SupabaseAuthApi
import com.example.touchmeactivity.registro.repository.AuthRepository
import com.example.touchmeactivity.screens.RegisterScreen
import com.example.touchmeactivity.uimodel.RegisterViewModel
import com.example.touchmeactivity.ui.theme.TouchMeActivityTheme
import com.example.touchmeactivity.screens.LoginScreen
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.navigation.compose.rememberNavController
import com.example.homeapp.screens.AppNavHost

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
                val navController = rememberNavController()
                var isRegister by remember { mutableStateOf(false) }

                AppNavHost(
                    navController = navController,
                    supabaseAuthApi = supabaseAuthApi,
                    isRegister = isRegister,
                    onRegisterClick = { isRegister = true },
                    onLoginSuccess = {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true } // Evita volver con "back"
                        }
                    }
                )
            }
        }
    }
}



