package com.example.poker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PokerScreen() {
    val valoresNumericos = mapOf(
        'A' to 14, 'K' to 13, 'Q' to 12, 'J' to 11, 'T' to 10,
        '9' to 9, '8' to 8, '7' to 7, '6' to 6, '5' to 5, '4' to 4, '3' to 3, '2' to 2
    )

    val baraja = listOf('S', 'H', 'D', 'C').flatMap { palo ->
        valoresNumericos.keys.map { "$it$palo" }
    }

    var mostrarResultadoDialogo by remember { mutableStateOf(false) }
    var mano1 by remember { mutableStateOf<List<String>>(emptyList()) }
    var mano2 by remember { mutableStateOf<List<String>>(emptyList()) }
    var resultado by remember { mutableStateOf("") }
    var jugada1 by remember { mutableStateOf("") }
    var jugada2 by remember { mutableStateOf("") }
    var repartido by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Título principal
        Text(
            text = "POKER",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        // Contenido principal: manos de los jugadores
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            if (repartido) {
                Column {
                    Text("Jugador 1 ($jugada1)", style = MaterialTheme.typography.titleMedium)
                    ManoView(mano1, resultado.contains("Jugador 1"))
                }

                Column {
                    Text("Jugador 2 ($jugada2)", style = MaterialTheme.typography.titleMedium)
                    ManoView(mano2, resultado.contains("Jugador 2"))
                }
            }
        }

        // Botón de repartir o volver a jugar
        Button(
            onClick = {
                val cartasMezcladas = baraja.shuffled()
                mano1 = cartasMezcladas.take(5)
                mano2 = cartasMezcladas.drop(5).take(5)

                jugada1 = evaluarJugada(mano1, valoresNumericos)
                jugada2 = evaluarJugada(mano2, valoresNumericos)

                resultado = determinarGanador(jugada1, jugada2, mano1, mano2, valoresNumericos)
                repartido = true
                mostrarResultadoDialogo = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            Text(if (repartido) "Volver a jugar" else "Repartir")
        }
    }

    // Diálogo con el resultado del juego
    if (mostrarResultadoDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarResultadoDialogo = false },
            confirmButton = {
                TextButton(onClick = { mostrarResultadoDialogo = false }) {
                    Text("OK")
                }
            },
            title = { Text("Resultado") },
            text = { Text(resultado) }
        )
    }
}



@Composable
fun ManoView(mano: List<String>, esGanadora: Boolean) {
    val nombresValores = mapOf(
        'A' to "ace", 'K' to "king", 'Q' to "queen", 'J' to "jack", 'T' to "10",
        '9' to "9", '8' to "8", '7' to "7", '6' to "6", '5' to "5", '4' to "4", '3' to "3", '2' to "2"
    )
    val nombresPalos = mapOf(
        'S' to "spades",
        'H' to "hearts",
        'D' to "diamonds",
        'C' to "clubs"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (esGanadora) Color(0xFFB2FF59) else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // ← menos espacio entre cartas
    ) {
        mano.forEach { carta ->
            val valorChar = carta[0]
            val paloChar = carta[1]
            val valorNombre = nombresValores[valorChar] ?: "unknown"
            val paloNombre = nombresPalos[paloChar] ?: "suit"
            val resourceName = "${valorNombre}_of_${paloNombre}"

            val context = LocalContext.current
            val resId = remember(resourceName) {
                context.resources.getIdentifier(resourceName, "drawable", context.packageName)
            }

            if (resId != 0) {
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = carta,
                    modifier = Modifier
                        .width(90.dp)  // ← más ancho
                        .height(126.dp) // ← más alto
                        .border(1.dp, Color.Black)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(90.dp, 126.dp)
                        .border(1.dp, Color.Red)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(carta)
                }
            }
        }
    }
}



fun evaluarJugada(cartas: List<String>, valoresNumericos: Map<Char, Int>): String {
    val valores = cartas.map { valoresNumericos[it[0]] ?: 0 }.sorted()
    val palos = cartas.map { it[1] }
    val esColor = palos.toSet().size == 1
    val esEscalera = valores.zipWithNext().all { it.second == it.first + 1 }

    val conteo = valores.groupingBy { it }.eachCount().values.sortedDescending()

    return when {
        esColor && esEscalera -> "Escalera de Color"
        conteo == listOf(4, 1) -> "Póker"
        conteo == listOf(3, 2) -> "Full"
        esColor -> "Color"
        esEscalera -> "Escalera"
        conteo == listOf(3, 1, 1) -> "Trío"
        conteo == listOf(2, 2, 1) -> "Doble Par"
        conteo == listOf(2, 1, 1, 1) -> "Par"
        else -> "Carta Alta"
    }
}

fun determinarGanador(
    jugada1: String, jugada2: String,
    mano1: List<String>, mano2: List<String>,
    valoresNumericos: Map<Char, Int>
): String {
    val ranking = mapOf(
        "Escalera de Color" to 9,
        "Póker" to 8,
        "Full" to 7,
        "Color" to 6,
        "Escalera" to 5,
        "Trío" to 4,
        "Doble Par" to 3,
        "Par" to 2,
        "Carta Alta" to 1
    )

    val valor1 = ranking[jugada1] ?: 0
    val valor2 = ranking[jugada2] ?: 0

    return when {
        valor1 > valor2 -> "Ganó Jugador 1 con $jugada1"
        valor2 > valor1 -> "Ganó Jugador 2 con $jugada2"
        else -> {
            val valores1 = mano1.map { valoresNumericos[it[0]] ?: 0 }.sortedDescending()
            val valores2 = mano2.map { valoresNumericos[it[0]] ?: 0 }.sortedDescending()
            for (i in 0..4) {
                if (valores1[i] > valores2[i]) return "Empate en jugada, gana Jugador 1 por carta alta"
                if (valores2[i] > valores1[i]) return "Empate en jugada, gana Jugador 2 por carta alta"
            }
            return "Empate total"
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PokerScreenPreview() {
    MaterialTheme {
        PokerScreen()
    }
}