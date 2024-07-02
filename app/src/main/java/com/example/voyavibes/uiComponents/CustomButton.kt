package com.example.voyavibes.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyavibes.R

data class CustomButton(
    val description: String,
    val image: Int
)

@Composable
fun CustomButtonView(customButton: CustomButton, onClick: () -> Unit = {}) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color(0xFF01635D), shape = RoundedCornerShape(15.dp))
                .clickable {
                    onClick()
                }
            ,
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = customButton.image),
                contentDescription = customButton.description,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
        Text(text = customButton.description,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 5.dp)
        )
    }

}

object BookingCustomButton {
    val booking = listOf(
        CustomButton("Trips", R.drawable.trip),
        CustomButton("Hotel", R.drawable.hotel),
        CustomButton("Transport", R.drawable.transport),
        CustomButton("Events", R.drawable.event)
    )
}

@Composable
fun BookingServicesButtons(
    onTripsClick: () -> Unit,
    onHotelClick: () -> Unit,
    onTransportClick: () -> Unit,
    onEventsClick: () -> Unit
){
    LazyRow(  modifier = Modifier
        .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(BookingCustomButton.booking) { customButton ->
            CustomButtonView(customButton, onClick = {
                when(customButton.description){
                    "Trips" -> onTripsClick()
                    "Hotel" -> onHotelClick()
                    "Transport" -> onTransportClick()
                    "Events" -> onEventsClick()
                }
            })
        }
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
   LazyRow(  modifier = Modifier
       .fillMaxSize(),
       horizontalArrangement = Arrangement.SpaceEvenly
   ) {
         items(BookingCustomButton.booking) { customButton ->
              CustomButtonView(customButton)
         }
   }
}





