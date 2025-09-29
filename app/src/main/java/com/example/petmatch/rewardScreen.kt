package com.example.petmatch
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colores específicos para pantalla de recompensas
val RewardBlueAccent = Color(0xFF4A90E2)
val RewardGreenAccent = Color(0xFF4CAF50)

data class RewardOption(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val category: RewardCategory
)

enum class RewardCategory {
    MONEY, PET_SERVICES, VOUCHERS, CUSTOM
}

@Composable
fun RecompensasScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onSaveReward: (RewardOption, String, String) -> Unit = { _, _, _ -> }
) {
    var selectedReward by remember { mutableStateOf<RewardOption?>(null) }
    var customAmount by remember { mutableStateOf("") }
    var customDescription by remember { mutableStateOf("") }
    var showCustomDialog by remember { mutableStateOf(false) }

    val rewardOptions = listOf(
        // Recompensas monetarias
        RewardOption("money_100", "Dinero en Efectivo", "$100 - $500 pesos", "💰", RewardCategory.MONEY),
        RewardOption("transfer", "Transferencia Bancaria", "Monto a acordar", "🏦", RewardCategory.MONEY),
        RewardOption("digital_pay", "Pago Digital", "PayPal, Mercado Pago, etc.", "📱", RewardCategory.MONEY),

        // Servicios para mascotas
        RewardOption("grooming", "Peluquería Canina", "Sesión completa de grooming", "✂️", RewardCategory.PET_SERVICES),
        RewardOption("vet", "Consulta Veterinaria", "Revisión médica gratuita", "🏥", RewardCategory.PET_SERVICES),
        RewardOption("pet_food", "Alimento Premium", "Bolsa de alimento de calidad", "🥘", RewardCategory.PET_SERVICES),
        RewardOption("toys", "Juguetes para Mascota", "Set de juguetes nuevos", "🎾", RewardCategory.PET_SERVICES),

        // Cupones y vales
        RewardOption("supermarket", "Vale de Supermercado", "$200 - $500 pesos", "🛒", RewardCategory.VOUCHERS),
        RewardOption("gas", "Cupón de Gasolina", "Para estación de servicio", "⛽", RewardCategory.VOUCHERS),
        RewardOption("amazon", "Gift Card Amazon", "Compras en línea", "📦", RewardCategory.VOUCHERS),

        // Personalizado
        RewardOption("custom", "Recompensa Personalizada", "Describe tu oferta", "✨", RewardCategory.CUSTOM)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Ofrecer Recompensa",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "Selecciona qué recompensa ofrecer a al dueño:",
            fontSize = 16.sp,
            color = DarkText,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de recompensas
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val groupedRewards = rewardOptions.groupBy { it.category }

            groupedRewards.forEach { (category, rewards) ->
                item {
                    Text(
                        text = when(category) {
                            RewardCategory.MONEY -> "💰 Recompensas Monetarias"
                            RewardCategory.PET_SERVICES -> "🐕 Servicios para Mascotas"
                            RewardCategory.VOUCHERS -> "🎫 Cupones y Vales"
                            RewardCategory.CUSTOM -> "✨ Personalizada"
                        },
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                items(rewards) { reward ->
                    RewardCard(
                        reward = reward,
                        isSelected = selectedReward?.id == reward.id,
                        onClick = {
                            selectedReward = reward
                            if (reward.id == "custom") {
                                showCustomDialog = true
                            }
                        }
                    )
                }
            }
        }

        // Campos adicionales si hay recompensa seleccionada
        selectedReward?.let { reward ->
            if (reward.category == RewardCategory.MONEY && reward.id != "custom") {
                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    Text(
                        text = "Monto específico (opcional):",
                        fontSize = 14.sp,
                        color = DarkText,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    BasicTextField(
                        value = customAmount,
                        onValueChange = { customAmount = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = androidx.compose.ui.text.TextStyle(
                            color = DarkText,
                            fontSize = 16.sp
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (customAmount.isEmpty()) {
                                    Text(
                                        text = "Ej: $300",
                                        color = Color.Gray,
                                        fontSize = 16.sp
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Text(
                    text = "Detalles adicionales (opcional):",
                    fontSize = 14.sp,
                    color = DarkText,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                BasicTextField(
                    value = customDescription,
                    onValueChange = { customDescription = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = DarkText,
                        fontSize = 14.sp
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.TopStart
                        ) {
                            if (customDescription.isEmpty()) {
                                Text(
                                    text = "Ej: Válido por 30 días, presentar identificación",
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón guardar
        Button(
            onClick = {
                selectedReward?.let { reward ->
                    onSaveReward(reward, customAmount, customDescription)
                }
            },
            enabled = selectedReward != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = RewardGreenAccent,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Publicar con Recompensa",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Barra de navegación inferior
        RewardsBottomNavigationBar(
            onBackClick = onBackClick,
            onHomeClick = onHomeClick,
            onMenuClick = onMenuClick
        )
    }

    // Dialog para recompensa personalizada
    if (showCustomDialog) {
        CustomRewardDialog(
            onDismiss = { showCustomDialog = false },
            onConfirm = { title, description ->
                selectedReward = RewardOption("custom", title, description, "✨", RewardCategory.CUSTOM)
                showCustomDialog = false
            }
        )
    }
}

@Composable
fun RewardCard(
    reward: RewardOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) RewardBlueAccent.copy(alpha = 0.1f) else Color.White
        ),
        border = if (isSelected)
            androidx.compose.foundation.BorderStroke(2.dp, RewardBlueAccent)
        else androidx.compose.foundation.BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono
            Text(
                text = reward.icon,
                fontSize = 32.sp,
                modifier = Modifier.padding(end = 16.dp)
            )

            // Información
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = reward.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )
                Text(
                    text = reward.description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Indicador de selección
            if (isSelected) {
                Text(
                    text = "✓",
                    fontSize = 24.sp,
                    color = RewardGreenAccent,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun CustomRewardDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Recompensa Personalizada",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column {
                Text(
                    text = "Título de la recompensa:",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                BasicTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (title.isEmpty()) {
                                Text(
                                    text = "Ej: Sesión de masajes",
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Descripción:",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                BasicTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.TopStart
                        ) {
                            if (description.isEmpty()) {
                                Text(
                                    text = "Describe tu recompensa...",
                                    color = Color.Gray,
                                    fontSize = 14.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        onConfirm(title, description)
                    }
                },
                enabled = title.isNotBlank()
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

// Componentes de navegación específicos para recompensas
@Composable
fun RewardsBottomNavigationBar(
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        RewardsNavigationButton(text = "←", onClick = onBackClick)
        RewardsNavigationButton(text = "🏠", onClick = onHomeClick)
        RewardsNavigationButton(text = "☰", onClick = onMenuClick)
    }
}

@Composable
fun RewardsNavigationButton(
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
fun RecompensasScreenPreview() {
    RecompensasScreen()
}