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

// Colores personalizados
val PinkBackground = Color(0xFFFFB3BA)
val DarkText = Color(0xFF2D2D2D)

// Pantalla principal con navegación
@Composable
fun PetMatchApp() {
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
        composable("register") {
            RegistroUsuarioScreen(
                onCancelClick = { navController.popBackStack() },
                onNextClick = { navController.navigate("mascota") }
            )
        }
        composable("mascota") {
            MascotaScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

// Pantalla de bienvenida (Primera imagen)
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
            onClick = { navController.navigate("register") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = DarkText
            ),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, DarkText)
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
            border = BorderStroke(2.dp, DarkText)
        ) {
            Text(
                text = "Iniciar Sesion",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Barra de navegación inferior
        BottomNavigationBar(
            onBackClick = { /* No action for welcome screen */ },
            onHomeClick = { /* Already at home */ },
            onMenuClick = { /* Menu action */ }
        )
    }
}

// Pantalla de inicio de sesión (Segunda imagen)
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
            CustomTextField(
                label = "Usuario",
                value = usuario,
                onValueChange = { usuario = it }
            )

            CustomTextField(
                label = "Contraseña",
                value = contrasena,
                onValueChange = { contrasena = it }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botones de inicio de sesión
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
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
                border = BorderStroke(1.dp, DarkText),
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
                border = BorderStroke(1.dp, DarkText),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Iniciar Sesion con Facebook")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Barra de navegación inferior
        BottomNavigationBar(
            onBackClick = { navController.popBackStack() },
            onHomeClick = { navController.navigate("welcome") },
            onMenuClick = { /* Menu action */ }
        )
    }
}

// Las funciones MascotaScreen y RegistroUsuarioScreen que ya tienes
@Composable
fun MascotaScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    var nombre by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Mascota",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Iconos de mascota
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            // Icono de perro (seleccionado)
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
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Campos de texto
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomTextField(
                label = "Nombre",
                value = nombre,
                onValueChange = { nombre = it }
            )

            CustomTextField(
                label = "Raza",
                value = raza,
                onValueChange = { raza = it }
            )

            CustomTextField(
                label = "Especie",
                value = especie,
                onValueChange = { especie = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección de validación
        Text(
            text = "Validación",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = DarkText,
            modifier = Modifier.align(Alignment.Start)
        )

        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "📷", fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo descripción
        Text(
            text = "Descripción",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = DarkText,
            modifier = Modifier.align(Alignment.Start)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.weight(1f))

        // Barra de navegación inferior
        BottomNavigationBar(
            onBackClick = onBackClick,
            onHomeClick = onHomeClick,
            onMenuClick = onMenuClick
        )
    }
}

@Composable
fun RegistroUsuarioScreen(
    onCancelClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    var nombreCompleto by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var noCel by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Registro Usuario",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Iconos de usuario
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            // Icono de persona
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFA500))
                    .border(3.dp, DarkText, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "👤",
                    fontSize = 32.sp
                )
            }

            // Icono de mascota
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
        }

        // Puntos indicadores
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            repeat(5) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(if (index == 0) DarkText else Color.Gray)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Campos de texto
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomTextField(
                label = "Nombre Completo",
                value = nombreCompleto,
                onValueChange = { nombreCompleto = it }
            )

            CustomTextField(
                label = "Edad",
                value = edad,
                onValueChange = { edad = it }
            )

            // Fecha de nacimiento con icono
            Column {
                Text(
                    text = "Fecha de Nacimiento",
                    fontSize = 14.sp,
                    color = DarkText,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = fechaNacimiento.ifEmpty { "DD/MM/YYYY" },
                            color = if (fechaNacimiento.isEmpty()) Color.Gray else DarkText
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "📅", fontSize = 16.sp)
                    }
                }
            }

            CustomTextField(
                label = "No. Cel",
                value = noCel,
                onValueChange = { noCel = it }
            )

            CustomTextField(
                label = "Ciudad",
                value = ciudad,
                onValueChange = { ciudad = it }
            )

            CustomTextField(
                label = "Estado",
                value = estado,
                onValueChange = { estado = it }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = onCancelClick,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = DarkText
                ),
                border = BorderStroke(1.dp, DarkText)
            ) {
                Text("Cancelar")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkText,
                    contentColor = Color.White
                )
            ) {
                Text("Siguiente")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Barra de navegación inferior
        BottomNavigationBar(
            onBackClick = onBackClick,
            onHomeClick = onHomeClick,
            onMenuClick = onMenuClick
        )
    }
}

@Composable
fun CustomTextField(
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
                textStyle = TextStyle(
                    color = DarkText,
                    fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavigationButton(text = "←", onClick = onBackClick)
        NavigationButton(text = "🏠", onClick = onHomeClick)
        NavigationButton(text = "☰", onClick = onMenuClick)
    }
}

@Composable
fun NavigationButton(
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