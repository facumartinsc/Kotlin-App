package com.example.touchmeactivity.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.touchmeactivity.R

@Composable
fun HomeScreen(navController: NavController) {
    val pagerState = rememberPagerState { 3 }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
    ) { page ->

        // Choose image based on page
        val imageRes = when (page) {
            0 -> R.drawable.pokir
            1 -> R.drawable.ball // Replace with your second image
            else -> R.drawable.pokir
        }
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Slide image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
//                    Text(
//                        text = "Juego ${page + 1}",
//                        style = MaterialTheme.typography.headlineMedium,
//                        color = MaterialTheme.colorScheme.onPrimary,
//                        modifier = Modifier.padding(8.dp)
//                    )
                }
            }
        }
    }


        Spacer(modifier = Modifier.height(290.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(3) { index ->
                val selected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (selected) 14.dp else 8.dp)
                        .animateContentSize()
                        .background(
                            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            shape = MaterialTheme.shapes.small
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(0.dp))

        Button(onClick = {
            when (pagerState.currentPage) {
                0 -> navController.navigate("game1")
                1 -> navController.navigate("game2")
            }
        }) {
            Text("Jugar")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(navController = rememberNavController())
    }
}
