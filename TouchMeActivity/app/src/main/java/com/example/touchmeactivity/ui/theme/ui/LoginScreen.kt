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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.touchmeactivity.R


@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .padding(16.dp)) {
        Login(Modifier.align(Alignment.Center))
    }
}

@Composable
fun Login(modifier: Modifier) {
    Column(modifier = modifier) {
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField()
        Spacer(modifier = Modifier.padding(4.dp))
        PasswordField()
        Spacer(modifier = Modifier.padding(8.dp))
        Register(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(8.dp))
        LoginButton()
    }
}

@Composable
fun LoginButton() {
    Button(onClick = { },
        modifier = Modifier.fillMaxWidth().height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF0069BB),
            contentColor = Color.White
        )
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun Register(modifier: Modifier) {
    Text(
        text = "¿No tienes una cuenta?",
        modifier = modifier.clickable {  },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        color = Color(0xFF0069BB)
    )
}

@Composable
fun PasswordField() {
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Contraseña") },
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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
fun EmailField() {
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF919193),
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
