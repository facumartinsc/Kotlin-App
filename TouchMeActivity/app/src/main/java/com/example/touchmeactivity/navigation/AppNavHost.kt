package com.example.homeapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.touchmeactivity.data.api.SupabaseAuthApi
import com.example.touchmeactivity.registro.repository.AuthRepository
import com.example.touchmeactivity.screens.HomeScreen
import com.example.touchmeactivity.screens.RegisterScreen
import com.example.touchmeactivity.uimodel.RegisterViewModel
import com.example.touchmeactivity.screens.LoginScreen
import screens.Game1Screen
import screens.Game2Screen


@Composable
fun AppNavHost(
    navController: NavHostController,
    supabaseAuthApi: SupabaseAuthApi,
    isRegister: Boolean,
    onRegisterClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    NavHost(navController = navController, startDestination = if (isRegister) "register" else "login") {
        composable("login") {
            LoginScreen(
                onRegisterClick = onRegisterClick,
                onLoginSuccess = onLoginSuccess
            )
        }
        composable("register") {
            val viewModel = RegisterViewModel(AuthRepository(supabaseAuthApi))
            RegisterScreen(viewModel = viewModel)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("game1") { Game1Screen() }
        composable("game2") { Game2Screen() }
    }
}



