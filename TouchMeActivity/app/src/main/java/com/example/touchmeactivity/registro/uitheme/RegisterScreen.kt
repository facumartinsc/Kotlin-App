package com.example.touchmeactivity.registro.uitheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.touchmeactivity.R
import com.example.touchmeactivity.registro.uimodel.RegisterUiState
import com.example.touchmeactivity.registro.viewmodel.RegisterViewModel


@Composable
fun RegisterScreen(viewModel: RegisterViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderImage(modifier = Modifier.size(100.dp))

            Spacer(Modifier.height(16.dp))
            TextField(
                value = uiState.username,
                onValueChange = viewModel::onUsernameChange,
                label = { Text("Nombre de usuario") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.colors(
//                    focusedContainerColor = Color(0xFF919193),
//                    unfocusedContainerColor = Color(0xFF919193),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                )
            )

            Spacer(Modifier.height(8.dp))

            TextField(
                value = uiState.email,
                onValueChange = viewModel::onEmailChange,
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.colors(
//                    focusedContainerColor = Color(0xFF919193),
//                    unfocusedContainerColor = Color(0xFF919193),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                )
            )

            Spacer(Modifier.height(8.dp))

            var passwordVisible by remember { mutableStateOf(false) }

            TextField(
                value = uiState.password,
                onValueChange = viewModel::onPasswordChange,
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = "Mostrar/ocultar contraseña")
                    }
                }
            )
            Spacer(Modifier.height(8.dp))

            var confirpasswordVisible by remember { mutableStateOf(false) }

            TextField(
                value = uiState.confirmPassword,
                onValueChange = viewModel::onConfirmPasswordChange,
                label = { Text("Confirmar Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.colors(
//                    focusedContainerColor = Color(0xFF919193),
//                    unfocusedContainerColor = Color(0xFF919193),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                visualTransformation = if (confirpasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (confirpasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { confirpasswordVisible = !confirpasswordVisible }) {
                        Icon(imageVector = icon, contentDescription = "Mostrar/ocultar contraseña")
                    }
                }
            )
            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { viewModel.register() },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                enabled = !uiState.isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0069BB),
                    contentColor = Color.White
                )

            ) {
                Text(if (uiState.isLoading) "Registrando..." else "Registrarse")
            }

            uiState.error?.let {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = it,
                    color = colorScheme.error,
                    style = typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
            uiState.successMessage?.let {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = it,
                    color = Color(0xFF4CAF50), // verde para éxito
                    style = typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

        }
    }
}


// imagen

@Composable
fun HeaderImage(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
//        Text("Registro", style = MaterialTheme.typography.titleLarge)
//        Spacer(Modifier.height(8.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(250.dp) // para telefono
            // modifier = Modifier.size(350.dp) // para la tablet :(

        )
    }
}






// codigo preview

@Composable
fun RegisterScreenPreviewContent(
    uiState: RegisterUiState,
    onUsernameChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onConfirmPasswordChange: (String) -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = uiState.username,
            onValueChange = onUsernameChange,
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color(0xFF919193),
                //unfocusedContainerColor = Color(0xFFFFFFFF),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            )
        )
        Spacer(Modifier.height(8.dp))

        TextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color(0xFF919193),
                //unfocusedContainerColor = Color(0xFFFFFFFF),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            )
        )
        Spacer(Modifier.height(8.dp))

        TextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color(0xFF919193),
                //unfocusedContainerColor = Color(0xFFFFFFFF),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(8.dp))

        TextField(
            value = uiState.confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = { Text("Confirmar Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color(0xFF919193),
                //unfocusedContainerColor = Color(0xFFFFFFFF),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            enabled = !uiState.isLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0069BB),
                contentColor = Color.White
            )
        ) {
            Text(if (uiState.isLoading) "Registrando..." else "Registrarse")
        }

        if (uiState.isLoading) {
            Spacer(Modifier.height(16.dp))
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        uiState.error?.let { errorMessage ->
            Text(
                text = errorMessage,
                color = Color.Red,
                style = typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
        }

        uiState.successMessage?.let { successMessage ->
            Text(
                text = successMessage,
                color = Color.Green,
                style = typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
        }


        uiState.error?.let {
            Spacer(Modifier.height(8.dp))
            Text(text = it, color = colorScheme.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val previewState = RegisterUiState(
        username = "xime y facu😤",
        email = "holita@gmail.com",
        password = "123456",
        confirmPassword = "123456",
        isLoading = false,
        error = null,
        successMessage = "Registro exitoso"  // <-- mensaje de éxito para preview
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderImage(modifier = Modifier.size(100.dp))

            Spacer(Modifier.height(16.dp))

            RegisterScreenPreviewContent(
                uiState = previewState,
                onUsernameChange = {},
                onEmailChange = {},
                onPasswordChange = {},
                onConfirmPasswordChange = {},
                onRegisterClick = {}
            )
        }
    }
}
