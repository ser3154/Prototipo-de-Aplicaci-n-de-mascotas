package com.example.petmatch

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color adicional para esta pantalla
val BlueAccent = Color(0xFF4A90E2)

@Composable
fun EditarMascotaScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    var nombre by remember { mutableStateOf("Max") }
    var raza by remember { mutableStateOf("Jack Russell Terrier") }
    var edad by remember { mutableStateOf("3 años") }
    var peso by remember { mutableStateOf("8.5 kg") }
    var color by remember { mutableStateOf("Blanco y café") }
    var descripcion by remember { mutableStateOf("Perro muy activo y juguetón") }

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
            text = "Editar Mascota",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Foto de la mascota
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(3.dp, DarkText, CircleShape)
                .clickable { /* Cambiar foto */ },
            contentAlignment = Alignment.Center
        ) {
            // Aquí iría la imagen real de la mascota
            Text(
                text = "🐕",
                fontSize = 48.sp
            )
        }

        Text(
            text = "Toca para cambiar foto",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campos editables
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EditableTextField(
                label = "Nombre",
                value = nombre,
                onValueChange = { nombre = it }
            )

            EditableTextField(
                label = "Raza",
                value = raza,
                onValueChange = { raza = it }
            )

            EditableTextField(
                label = "Edad",
                value = edad,
                onValueChange = { edad = it }
            )

            EditableTextField(
                label = "Peso",
                value = peso,
                onValueChange = { peso = it }
            )

            EditableTextField(
                label = "Color",
                value = color,
                onValueChange = { color = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección de vacunas
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Para verificar a su mascota favor de subir un documento que muestre las vacunas de su mascota",
                    fontSize = 14.sp,
                    color = DarkText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Botón de subir documento
                Button(
                    onClick = { /* Subir documento */ },
                    modifier = Modifier.size(80.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(2.dp, BlueAccent)
                ) {
                    Text(
                        text = "📄✓",
                        fontSize = 24.sp,
                        color = BlueAccent
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Este proceso puede tardar unas horas o pocos días",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Campo descripción
        Column {
            Text(
                text = "Descripción",
                fontSize = 14.sp,
                color = DarkText,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                BasicTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    modifier = Modifier.fillMaxSize(),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = DarkText,
                        fontSize = 14.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón guardar
        Button(
            onClick = onSaveClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkText,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Guardar Cambios",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Barra de navegación inferior
        EditBottomNavigationBar(
            onBackClick = onBackClick,
            onHomeClick = onHomeClick,
            onMenuClick = onMenuClick
        )
    }
}

@Composable
fun EditarInfoDuenoScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    var nombreCompleto by remember { mutableStateOf("Juan Pérez González") }
    var email by remember { mutableStateOf("juan.perez@email.com") }
    var telefono by remember { mutableStateOf("123-456-7890") }
    var direccion by remember { mutableStateOf("Calle Principal 123") }
    var ciudad by remember { mutableStateOf("Ciudad de México") }
    var estado by remember { mutableStateOf("CDMX") }
    var codigoPostal by remember { mutableStateOf("12345") }

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
            text = "Editar Información Personal",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Foto de perfil
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(3.dp, DarkText, CircleShape)
                .clickable { /* Cambiar foto */ },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "👤",
                fontSize = 48.sp
            )
        }

        Text(
            text = "Toca para cambiar foto",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campos editables
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EditableTextField(
                label = "Nombre Completo",
                value = nombreCompleto,
                onValueChange = { nombreCompleto = it }
            )

            EditableTextField(
                label = "Email",
                value = email,
                onValueChange = { email = it }
            )

            EditableTextField(
                label = "Teléfono",
                value = telefono,
                onValueChange = { telefono = it }
            )

            EditableTextField(
                label = "Dirección",
                value = direccion,
                onValueChange = { direccion = it }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                EditableTextField(
                    label = "Ciudad",
                    value = ciudad,
                    onValueChange = { ciudad = it },
                    modifier = Modifier.weight(1f)
                )

                EditableTextField(
                    label = "Estado",
                    value = estado,
                    onValueChange = { estado = it },
                    modifier = Modifier.weight(1f)
                )
            }

            EditableTextField(
                label = "Código Postal",
                value = codigoPostal,
                onValueChange = { codigoPostal = it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección de verificación de identidad
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Verificación de Identidad",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Para mayor seguridad, sube una foto de tu identificación oficial",
                    fontSize = 14.sp,
                    color = DarkText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Botón de subir ID
                Button(
                    onClick = { /* Subir ID */ },
                    modifier = Modifier.size(80.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(2.dp, BlueAccent)
                ) {
                    Text(
                        text = "🆔",
                        fontSize = 24.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "La verificación puede tardar 1-2 días hábiles",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onBackClick,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = DarkText
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, DarkText),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkText,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Guardar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Barra de navegación inferior
        EditBottomNavigationBar(
            onBackClick = onBackClick,
            onHomeClick = onHomeClick,
            onMenuClick = onMenuClick
        )
    }
}

@Composable
fun EditableTextField(
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

// Componentes reutilizables específicos para estas pantallas
@Composable
fun EditBottomNavigationBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        EditNavigationButton(text = "←", onClick = onBackClick)
        EditNavigationButton(text = "🏠", onClick = onHomeClick)
        EditNavigationButton(text = "☰", onClick = onMenuClick)
    }
}

@Composable
fun EditNavigationButton(
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
fun EditarMascotaScreenPreview() {
    EditarMascotaScreen()
}

@Preview(showBackground = true)
@Composable
fun EditarInfoDuenoScreenPreview() {
    EditarInfoDuenoScreen()
}