package com.example.touchmeactivity.screens.tocame

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.touchmeactivity.R
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun TocameScreen() {
    var puntos by remember { mutableStateOf(0) }
    var juegoIniciado by remember { mutableStateOf(false) }
    var offsetX by remember { mutableStateOf(0.dp) }
    var offsetY by remember { mutableStateOf(0.dp) }
    var tiempoRestante by remember { mutableStateOf(0) }

    val density = LocalDensity.current

    // Temporizador cuando inicia el juego
    LaunchedEffect(juegoIniciado) {
        tiempoRestante = 30
        if (juegoIniciado) {
            while (tiempoRestante > 0) {
                delay(1000L)
                tiempoRestante--
            }
            juegoIniciado = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        // Encabezado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Facu", fontSize = 24.sp)
            Text("$tiempoRestante", fontSize = 24.sp)
            Text("Puntaje: $puntos", fontSize = 24.sp, textAlign = TextAlign.End)
        }

        // Zona de juego
        BoxWithConstraints(
            modifier = Modifier
                .height(650.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                //.padding(vertical = 16.dp)
                //.background(MaterialTheme.colorScheme.surfaceVariant),
                .background(Color(0x750D47A1)),
            contentAlignment = Alignment.TopStart
        ) {
            val maxWidthPx = constraints.maxWidth
            val maxHeightPx = constraints.maxHeight

            if (juegoIniciado && tiempoRestante > 0) {

                Image(
                    painter = painterResource(id = R.drawable.ball),
                    contentDescription = "Pelota",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(offsetX, offsetY)
                        .clickable {
                            puntos++

                            val imageSizePx = with(density) { 80.dp.toPx().toInt() }
                            val randX = Random.nextInt(0, maxWidthPx - imageSizePx)
                            val randY = Random.nextInt(0, maxHeightPx - imageSizePx)
                            offsetX = with(density) { randX.toDp() }
                            offsetY = with(density) { randY.toDp() }
                        }
                )
            }
        }

        //Spacer(modifier = Modifier.height(24.dp))

        // Botones abajo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = { /* TODO: Ir a tabla de puntajes */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("Tabla", color = Color.White)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {
                    puntos = 0
                    juegoIniciado = true
                },
                enabled = !juegoIniciado,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("Jugar")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TocameScreenPreview() {
    MaterialTheme {
        TocameScreen()
    }
}
