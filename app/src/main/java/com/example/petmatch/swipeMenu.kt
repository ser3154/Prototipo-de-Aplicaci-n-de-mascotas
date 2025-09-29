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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
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
import kotlin.math.abs


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
fun SwipeableCard(
    petName: String,
    petEmoji: String,
    petAge: String,
    petOwner: String,
    petDescription: String,
    petColor: Color,
    petTags: List<String>,
    petDistance: String,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    var rotation by remember { mutableStateOf(0f) }

    val swipeThreshold = 300f

    // Calcular rotación basada en el desplazamiento
    val maxRotation = 15f
    rotation = (offsetX / swipeThreshold) * maxRotation

    Box(
        modifier = Modifier
            .width(320.dp)
            .height(480.dp)
            .offset { IntOffset(offsetX.toInt(), 0) }
            .rotate(rotation)
            .shadow(16.dp, RoundedCornerShape(32.dp))
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                    },
                    onDragEnd = {
                        when {
                            offsetX > swipeThreshold -> {
                                onSwipeRight()
                                offsetX = 0f
                                rotation = 0f
                            }
                            offsetX < -swipeThreshold -> {
                                onSwipeLeft()
                                offsetX = 0f
                                rotation = 0f
                            }
                            else -> {
                                offsetX = 0f
                                rotation = 0f
                            }
                        }
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Emoji/Imagen con fondo decorativo
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .background(petColor.copy(alpha = 0.2f), CircleShape)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(petEmoji, fontSize = 64.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Nombre y edad
            Text(
                "$petName, $petAge",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Dueño
            Text(
                "Dueño: $petOwner",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tags
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center
            ) {
                petTags.forEach { tag ->
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .height(28.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = petColor.copy(alpha = 0.1f)),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Text(
                            text = tag,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = petColor
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Descripción
            Text(
                petDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Información adicional
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                InfoItem(icon = "📍", text = petDistance)
                InfoItem(icon = "⚡", text = "Activo")
                InfoItem(icon = "❤️", text = "Saludable")
            }
        }

        // Indicadores de swipe
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(abs(offsetX) / swipeThreshold * 0.8f)
        ) {
            if (offsetX > 0) {
                // Swipe right - Like
                Text(
                    "LIKE",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 32.dp)
                        .rotate(-15f),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50)
                )
            } else if (offsetX < 0) {
                // Swipe left - Nope
                Text(
                    "NOPE",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 32.dp)
                        .rotate(15f),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF5252)
                )
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
            .background(color = PinkBackground)
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
fun InfoItem(icon: String, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = icon,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
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

    PetSwipeScreen(
        onProfileClick = {},
        onConfigClick = {},
        onMatchesClick = {}
    )

}

@Composable
fun PetSwipeScreen(
    onProfileClick: () -> Unit,
    onConfigClick: () -> Unit,
    onMatchesClick: () -> Unit
) {
}