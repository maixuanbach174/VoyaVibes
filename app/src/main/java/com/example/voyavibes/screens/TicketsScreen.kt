package com.example.voyavibes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyavibes.uiComponents.BookingServicesCards
import com.example.voyavibes.uiComponents.CustomCardView

@Composable
fun TicketsScreen(innerPadding: PaddingValues,
                  onTripsClick: () -> Unit = {},
                    onHotelsClick: () -> Unit = {},
                    onTransportClick: () -> Unit = {},
                    onEventClick: () -> Unit = {},
                  ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color(0x65D3D3D3))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bookings",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold
            )
            BookingServicesCards( onTripsClick, onHotelsClick, onTransportClick, onEventClick)
        }
    }
}

@Preview
@Composable
fun PreviewTicketsScreen() {
    TicketsScreen(PaddingValues(0.dp))
}
