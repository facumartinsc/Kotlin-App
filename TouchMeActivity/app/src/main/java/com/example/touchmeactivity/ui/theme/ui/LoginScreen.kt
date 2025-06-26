package com.example.touchmeactivity.ui.theme.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.touchmeactivity.R
import com.example.touchmeactivity.ui.viewmodel.LoginViewModel


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit
) {
    val viewModel: LoginViewModel = viewModel()

    Box(
        modifier
            .fillMaxSize()
            .padding(16.dp)) {
        Login(Modifier.align(Alignment.Center), viewModel = viewModel, onRegisterClick = onRegisterClick)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, onRegisterClick: () -> Unit) {
    val errorMessage by viewModel.errorMessage.observeAsState()

    Column(modifier = modifier) {
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(viewModel)
        Spacer(modifier = Modifier.padding(4.dp))
        PasswordField(viewModel)
        Spacer(modifier = Modifier.padding(8.dp))
        //Register(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(8.dp))
        LoginButton { viewModel.onLoginClicked() }

        Register(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onRegisterClick
        )

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2F75BC),
            contentColor = Color.White
        )
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun Register(modifier: Modifier, onClick: () -> Unit) {
    Text(
        text = "¿No tienes una cuenta?",
        modifier = modifier.clickable { onClick() },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        color = Color(0xFF2F75BC)
    )
}

@Composable
fun PasswordField(viewModel: LoginViewModel) {
    val password by viewModel.password.observeAsState("")
    var passwordVisible by remember { mutableStateOf(false) }
    TextField(
        value = password, onValueChange = { viewModel.onPasswordChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Contraseña") },
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = icon, contentDescription = "Mostrar/ocultar contraseña")
            }
        },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            //focusedContainerColor = Color(0xFF2196F3),
            //unfocusedContainerColor = Color(0xFFFFFFFF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White
        )
    )
}


@Composable
fun EmailField(viewModel: LoginViewModel) {

    val email by viewModel.email.observeAsState("")
    TextField(
        value = email, onValueChange = {viewModel.onEmailChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            //focusedContainerColor = Color(0xFF919193),
            //unfocusedContainerColor = Color(0xFFFFFFFF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White
        )
    )
}


@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
        modifier = modifier)

}

@Preview
@Composable
fun SimpleComposablePreview() {
    LoginScreen(onRegisterClick = {})
}
