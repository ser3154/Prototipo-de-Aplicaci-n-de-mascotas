package com.example.petmatch

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConfigScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Configuración de perfíl",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(3.dp, DarkText, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "👤",
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )

        }

        Text(
            text = "Pedro 30y",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        Button(
            onClick = {  },
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
                text = "Mascotas registradas",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {  },
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
                text = "Agregar Mascotas",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {  },
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
                text = "Editar información",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun MatchesScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("💬 Matches")
    }
}

@Composable
fun ProfileScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("👤 Perfil")
    }
}


@Preview(showBackground = true)
@Composable
fun ConfigPreview() {
    ConfigScreen()
}

