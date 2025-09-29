package com.example.petmatch

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petapp.ui.screens.MascotaScreen
import com.example.petapp.ui.screens.RegistroUsuarioScreen

// Colores personalizados
val PinkBackground = Color(0xFFFFB3BA)
val DarkText = Color(0xFF2D2D2D)

// Pantalla principal con navegación simplificada
@Composable
fun SimplePetMatchApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("mascota") {
            MascotaScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("registro") {
            RegistroUsuarioScreen(
                onCancelClick = { navController.popBackStack() },
                onNextClick = { navController.navigate("mascota") }
            )
        }
    }
}

// Pantalla de bienvenida
@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "PetMatch",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Imagen circular con mascotas
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(3.dp, DarkText, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🐕🐱🐦🐹",
                fontSize = 48.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Botón Crear una Cuenta
        Button(
            onClick = { navController.navigate("registro") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = DarkText
            ),
            shape = RoundedCornerShape(8.dp),
            border = androidx.compose.foundation.BorderStroke(2.dp, DarkText)
        ) {
            Text(
                text = "Crear una Cuenta",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Iniciar Sesion
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = DarkText
            ),
            shape = RoundedCornerShape(8.dp),
            border = androidx.compose.foundation.BorderStroke(2.dp, DarkText)
        ) {
            Text(
                text = "Iniciar Sesion",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Barra de navegación inferior
        SimpleBottomNavigationBar(
            onBackClick = { /* No action for welcome screen */ },
            onHomeClick = { /* Already at home */ },
            onMenuClick = { /* Menu action */ }
        )
    }
}

// Pantalla de inicio de sesión
@Composable
fun LoginScreen(navController: NavController) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Inicio de Sesion",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        // Imagen circular con persona y mascota
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(3.dp, DarkText, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "👤🐕",
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Campos de texto
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            SimpleTextField(
                label = "Usuario",
                value = usuario,
                onValueChange = { usuario = it }
            )

            SimpleTextField(
                label = "Contraseña",
                value = contrasena,
                onValueChange = { contrasena = it }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de iniciar sesión normal
        Button(
            onClick = { navController.navigate("mascota") },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkText,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botones de redes sociales
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Botón Google
            OutlinedButton(
                onClick = { navController.navigate("mascota") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = DarkText
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, DarkText),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Iniciar Sesion con Google")
            }

            // Botón Facebook
            OutlinedButton(
                onClick = { navController.navigate("mascota") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = DarkText
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, DarkText),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Iniciar Sesion con Facebook")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Barra de navegación inferior
        SimpleBottomNavigationBar(
            onBackClick = { navController.popBackStack() },
            onHomeClick = { navController.navigate("welcome") },
            onMenuClick = { /* Menu action */ }
        )
    }
}

// TextField simplificado para evitar conflictos
@Composable
fun SimpleTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = DarkText,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = androidx.compose.ui.text.TextStyle(
                    color = DarkText,
                    fontSize = 16.sp
                )
            )
        }
    }
}

// Barra de navegación simplificada para evitar conflictos
@Composable
fun SimpleBottomNavigationBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SimpleNavigationButton(text = "←", onClick = onBackClick)
        SimpleNavigationButton(text = "🏠", onClick = onHomeClick)
        SimpleNavigationButton(text = "☰", onClick = onMenuClick)
    }
}

@Composable
fun SimpleNavigationButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = DarkText,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController)
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}