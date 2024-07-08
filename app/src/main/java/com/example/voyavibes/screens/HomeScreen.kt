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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voyavibes.uiComponents.BookingServicesButtons
import com.example.voyavibes.uiComponents.MinimalDialog

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    onTransportClick: () -> Unit,
){
    val openMinimalDialog = remember { mutableStateOf(false) }
    Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x65D3D3D3))
        ) {
            Column(
                Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
            )
            {
                Spacer(modifier = Modifier.padding(15.dp))
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
                BookingServicesButtons( {
                    openMinimalDialog.value = true
                },
                {
                    openMinimalDialog.value = true
                }, onTransportClick, {
                    openMinimalDialog.value = true
                    })


            }
        }
    when {
        openMinimalDialog.value -> {
            MinimalDialog(
                onDismissRequest = { openMinimalDialog.value = false },
                text = "This feature is developing"
            )
        }
    }
}

