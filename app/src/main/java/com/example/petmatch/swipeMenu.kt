package com.example.petmatch

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petapp.ui.screens.DarkText
import com.example.petapp.ui.screens.PinkBackground


@Composable
fun PetScreen(navController: NavController) {
    PetSwipeScreen(
        onConfigClick = {
            // Aquí pones navegación a Configuración
            navController.navigate("config")
        },
        onMatchesClick = {
            // Aquí pones navegación a Matches
            navController.navigate("matches")
        },
        onProfileClick = {
            // Aquí pones navegación a Perfil
            navController.navigate("profile")
        }
    )
}
@Composable
fun PetSwipeScreen(
    onConfigClick: () -> Unit,
    onMatchesClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    var currentPet by remember { mutableStateOf(0) }
    val pets = listOf(
        Pair("Doggo", "🐶"),
        Pair("Kitty", "🐱"),
        Pair("Bunny", "🐰")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE3F2FD), Color(0xFFBBDEFB))
                )
            )
    ) {
        // Botón de Configuración arriba a la derecha
        IconButton(
            onClick = onConfigClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Settings, contentDescription = "Config")
        }

        // Card con la mascota
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(32.dp)
                .align(Alignment.Center),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(pets[currentPet].second, fontSize = 64.sp)
                Text(pets[currentPet].first, fontSize = 28.sp)
            }
        }

        // Botones de acción tipo Tinder
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    currentPet = (currentPet + 1) % pets.size
                },
                colors = ButtonDefaults.buttonColors(Color.Red),
                shape = CircleShape
            ) {
                Text("❌", fontSize = 24.sp)
            }

            Button(
                onClick = {
                    // Guardar Match
                },
                colors = ButtonDefaults.buttonColors(Color.Green),
                shape = CircleShape
            ) {
                Text("❤️", fontSize = 24.sp)
            }

            Button(
                onClick = onMatchesClick,
                colors = ButtonDefaults.buttonColors(Color.Magenta),
                shape = CircleShape
            ) {
                Text("💬", fontSize = 24.sp)
            }

            Button(
                onClick = onProfileClick,
                colors = ButtonDefaults.buttonColors(Color.Blue),
                shape = CircleShape
            ) {
                Text("👤", fontSize = 24.sp)
            }
        }
    }
}

@Composable
fun TopMenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White.copy(alpha = 0.85f),
            contentColor = Color.Black
        ),
        modifier = Modifier.height(40.dp)
    ) {
        Text(text, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ActionButton(icon: String, color: Color, onClick: () -> Unit) {
    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 1.2f else 1f,
        label = "button-scale"
    )

    Box(
        modifier = Modifier
            .size(80.dp)
            .scale(scale)
            .clip(CircleShape)
            .background(color=PinkBackground)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        tryAwaitRelease()
                        pressed = false
                        onClick()
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(icon, fontSize = 28.sp, color = Color.White)
    }
}
@Composable
fun SwipeableCard(
    petName: String,
    petEmoji: String,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .size(300.dp, 400.dp)
            .offset { IntOffset(offsetX.toInt(), 0) }
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        when {
                            offsetX > 300f -> { onSwipeRight(); offsetX = 0f }
                            offsetX < -300f -> { onSwipeLeft(); offsetX = 0f }
                            else -> offsetX = 0f
                        }
                    }
                ) { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(petEmoji, fontSize = 96.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(petName, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PetScreen(){


}