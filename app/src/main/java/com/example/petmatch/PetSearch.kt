package com.example.petmatch

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petapp.ui.screens.BottomNavigationBar
import com.example.petapp.ui.screens.DarkText
import com.example.petapp.ui.screens.PinkBackground
import com.example.petmatch.ui.theme.PetMatchTheme

@Composable
fun PetSearchScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Preferencias",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(
            Modifier
                .border(
                    width = 3.dp,
                    color = Color(Color.Black.value)
                )
                .background(Color(0xFFF5C8BA))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "#Actividades en casa",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "#Paseos Nocturnos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "#Actividades al aire libre",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "#Playa",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        Text(
            text = "Selección",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(
            Modifier
                .padding(bottom = 25.dp)
                .border(
                    width = 3.dp,
                    color = Color(Color.Black.value)
                )
                .background(Color(0xFFF5C8BA))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "#Actividades en casa",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "#Paseos Nocturnos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "#Actividades al aire libre",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "#Playa",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(
            onBackClick = onBackClick,
            onHomeClick = onHomeClick,
            onMenuClick = onMenuClick
        )
    }
}

@Composable
fun PetInfoScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            Modifier
                .padding(top = 50.dp)
                .border(
                    width = 3.dp,
                    color = Color(Color.Black.value)
                )
                .background(Color(0xFFF5C8BA))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Box(
                modifier = Modifier
                    .padding(top = 25.dp)
                    .size(160.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFA500))
                    .border(3.dp, DarkText, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🐕",
                    fontSize = 86.sp
                )
            }

            Text(
                text = "Firulais",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(vertical = 15.dp)
            )

            Text(
                text = "Perro",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(bottom = 25.dp)
            )
        }

        Column(
            Modifier
                .padding(bottom = 25.dp)
                .border(
                    width = 3.dp,
                    color = Color(Color.Black.value)
                )
                .background(Color(0xFFF5C8BA))
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Descripción",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "Es un perro...",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "#Actividades en casa",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.padding(top = 16.dp, bottom = 30.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(
            onBackClick = onBackClick,
            onHomeClick = onHomeClick,
            onMenuClick = onMenuClick
        )
    }
}

@Composable
fun PetResultsScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Coincidencias",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Row(
            Modifier
                .padding(16.dp),
            Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFA500))
                    .border(3.dp, DarkText, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🐕",
                    fontSize = 32.sp
                )
            }

            // Icono de gato
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(2.dp, Color.Gray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🐱",
                    fontSize = 32.sp
                )
            }

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF97EDFF))
                    .border(3.dp, Color.Black, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🐹",
                    fontSize = 32.sp
                )
            }
        }

        Text(
            text = "Chats",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(
            onBackClick = onBackClick,
            onHomeClick = onHomeClick,
            onMenuClick = onMenuClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PetSearchPreview() {
    PetMatchTheme {
        PetSearchScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PetInfoPreview() {
    PetMatchTheme {
        PetInfoScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PetResultPreview() {
    PetMatchTheme {
        PetResultsScreen()
    }
}