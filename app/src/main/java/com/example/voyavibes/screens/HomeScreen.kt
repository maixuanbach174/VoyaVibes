package com.example.voyavibes.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import com.example.voyavibes.uiComponents.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voyavibes.uiComponents.BookingServicesButtons

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    onTripsClick: () -> Unit,
    onHotelClick: () -> Unit,
    onTransportClick: () -> Unit,
    onEventsClick: () -> Unit,
){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
                .background(Color(0x65D3D3D3))
        ) {
            Column(
                Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
            )
            {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Welcome to VoyaVibes!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                SearchBar()
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Booking Services",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.padding(10.dp))
                BookingServicesButtons( onTripsClick, onHotelClick, onTransportClick, onEventsClick)


            }
        }
}

