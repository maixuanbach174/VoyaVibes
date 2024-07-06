package com.example.voyavibes.uiComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Divider as HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voyavibes.R

data class CustomCard(
    val description: String,
    val image: Int
)

@Composable
fun CustomCardView(customCard: CustomCard, onClick: () -> Unit = {}) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(15.dp))
                .clickable {
                    onClick()
                }
                .padding(20.dp)
            ,
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = customCard.image),
                    contentDescription = customCard.description,
                    modifier = Modifier.size(270.dp)
                )
                HorizontalDivider(thickness = 2.dp)
                Text(text = customCard.description,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
            }

        }
    }

}

object BookingCustomCard {
    val booking = listOf(
        CustomCard("Trips", R.drawable.tripcard),
        CustomCard("Hotel", R.drawable.hotelcard),
        CustomCard("Transport", R.drawable.transportcard),
        CustomCard("Events", R.drawable.eventcard)
    )
}

@Composable
fun BookingServicesCards(
    onTripClick: () -> Unit = {},
    onHotelClick: () -> Unit = {},
    onTransportClick: () -> Unit = {},
    onEventClick: () -> Unit = {}
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(start = 25.dp, end = 25.dp, bottom = 25.dp, top = 0.dp)
    ) {
        items(BookingCustomCard.booking) { customCard ->
            CustomCardView(customCard, onClick = {
                when(customCard.description){
                    "Trips" -> onTripClick()
                    "Hotel" -> onHotelClick()
                    "Transport" -> onTransportClick()
                    "Events" -> onEventClick()
                }
            })
        }
    }
}

@Preview
@Composable
fun BookingServicesCardsPreview() {
    BookingServicesCards()
}
