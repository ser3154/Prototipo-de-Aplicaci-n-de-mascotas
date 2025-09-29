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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
            navController.navigate("config")
        },
        onMatchesClick = {
            navController.navigate("matches")
        },
        onProfileClick = {
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

    // Datos mejorados de mascotas
    data class Pet(
        val name: String,
        val emoji: String,
        val gradient: List<Color>,
        val description: String,
        val age: String,
        val owner: String
    )

    val pets = listOf(
        Pet(
            "Doggo", "🐶",
            listOf(Color(0xFFFFF0F5), Color(0xFFFFB6C1)),
            "Perro juguetón y amigable que adora correr en el parque",
            "3 años", "Carlos"
        ),
        Pet(
            "Kitty", "🐱",
            listOf(Color(0xFFF0F8FF), Color(0xFFB9D9EB)),
            "Gatita tranquila y cariñosa, ideal para compañía",
            "2 años", "Lucía"
        ),
        Pet(
            "Bunny", "🐰",
            listOf(Color(0xFFF5F0FF), Color(0xFFD8BFD8)),
            "Conejito curioso que ama las zanahorias y explorar",
            "1 año", "Andrés"
        )
    )

    val pet = pets[currentPet]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = pet.gradient
                )
            )
    ) {
        // Header mejorado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo/Título con gradiente
            Text(
                "PetMatch",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFFF69B4), Color(0xFFFFB6C1))
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = Color.White
            )

            // Botón de configuración mejorado
            GradientIconButton(
                icon = Icons.Default.Settings,
                gradient = listOf(Color(0xFF6A5ACD), Color(0xFF9370DB)),
                onClick = onConfigClick
            )
        }

        // Card principal mejorada
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(480.dp)
                .align(Alignment.Center)
                .shadow(24.dp, RoundedCornerShape(32.dp)),
            shape = RoundedCornerShape(32.dp),
            elevation = CardDefaults.cardElevation(0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Emoji con fondo gradiente
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(pet.gradient[1].copy(alpha = 0.3f), Color.Transparent)
                            ),
                            shape = CircleShape
                        )
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(pet.emoji, fontSize = 72.sp)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Nombre con gradiente
                Text(
                    pet.name,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFFFF69B4), Color(0xFFFFB6C1))
                            )
                        )
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Información adicional
                Text(
                    "${pet.age} • Dueño: ${pet.owner}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Descripción
                Text(
                    pet.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Tags decorativos
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GradientChip(text = "Amigable", gradient = listOf(Color(0xFFFFB6C1), Color(0xFFFF69B4)))
                    GradientChip(text = "Juguetón", gradient = listOf(Color(0xFFB9D9EB), Color(0xFF87CEEB)))
                    GradientChip(text = "Activo", gradient = listOf(Color(0xFFD8BFD8), Color(0xFFDA70D6)))
                }
            }
        }

        // Botones de acción mejorados en la parte inferior
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
                .fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Botón Rechazar
            GradientActionButton(
                icon = "❌",
                gradient = listOf(Color(0xFFFF6B6B), Color(0xFFFF8E8E)),
                onClick = { currentPet = (currentPet + 1) % pets.size }
            )

            // Botón Match
            GradientActionButton(
                icon = "❤️",
                gradient = listOf(Color(0xFF51CF66), Color(0xFF8CE99A)),
                onClick = { /* Guardar Match */ }
            )

            // Botón Mensajes
            GradientActionButton(
                icon = "💬",
                gradient = listOf(Color(0xFF339AF0), Color(0xFF74C0FC)),
                onClick = onMatchesClick
            )

            // Botón Perfil
            GradientActionButton(
                icon = "👤",
                gradient = listOf(Color(0xFFCC5DE8), Color(0xFFE599F7)),
                onClick = onProfileClick
            )
        }

        // Indicador de progreso
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(pets.size) { index ->
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(
                            color = if (index == currentPet) pet.gradient[1] else Color.White.copy(alpha = 0.5f),
                            shape = CircleShape
                        )
                        .shadow(4.dp, CircleShape)
                )
            }
        }
    }
}

// Componente de botón con gradiente mejorado
@Composable
fun GradientActionButton(
    icon: String,
    gradient: List<Color>,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.9f else 1f,
        label = "button-scale"
    )

    Box(
        modifier = Modifier
            .size(70.dp)
            .scale(scale)
            .shadow(12.dp, CircleShape)
            .clip(CircleShape)
            .background(
                brush = Brush.verticalGradient(colors = gradient)
            )
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
        Text(icon, fontSize = 26.sp, color = Color.White)
    }
}

// Botón de icono con gradiente
@Composable
fun GradientIconButton(
    icon: ImageVector,
    gradient: List<Color>,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.9f else 1f,
        label = "icon-button-scale"
    )

    Box(
        modifier = Modifier
            .size(50.dp)
            .scale(scale)
            .shadow(8.dp, CircleShape)
            .clip(CircleShape)
            .background(
                brush = Brush.verticalGradient(colors = gradient)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

// Chip con gradiente
@Composable
fun GradientChip(text: String, gradient: List<Color>) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.horizontalGradient(colors = gradient)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

// Tu ActionButton original mejorado
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
            .shadow(16.dp, CircleShape)
            .clip(CircleShape)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(color, color.copy(alpha = 0.8f))
                )
            )
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

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp,dpi=420")
@Composable
fun PetSwipeScreenPreview() {
    MaterialTheme {
        PetSwipeScreen(
            onConfigClick = {},
            onMatchesClick = {},
            onProfileClick = {}
        )
    }
}