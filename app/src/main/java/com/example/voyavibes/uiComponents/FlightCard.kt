package com.example.voyavibes.uiComponents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voyavibes.Data.Flight
import com.example.voyavibes.Data.Flights
import com.example.voyavibes.R
import androidx.compose.material3.Divider as HorizontalDivider

@Composable
fun FlightAirportCard(flight: Flight) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.White)
            .padding(top = 15.dp, start = 0.dp, end = 0.dp, bottom = 15.dp),
        contentAlignment = Alignment.Center
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = flight.departureCode,
                    color = colorResource(id = R.color.green_500),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = flight.departureName,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.loading),
                contentDescription = "",
                tint = colorResource(id = R.color.peach_300),
                modifier = Modifier.weight(1f)
            )
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = flight.arrivalCode,
                    color = colorResource(id = R.color.green_500),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = flight.arrivalName,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }
        }

    }
}

@Composable
fun TimeCard(flight: Flight) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(top = 15.dp, start = 5.dp, end = 0.dp, bottom = 15.dp),
    ){
        Row(
//            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Date",
                    color = colorResource(id = R.color.green_500),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = flight.date,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Departure",
                    color = colorResource(id = R.color.green_500),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = flight.departureTime,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Price",
                    color = colorResource(id = R.color.green_500),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = "$" + flight.price.toString(),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Number",
                    color = colorResource(id = R.color.green_500),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = flight.flightNumber,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }

        }
    }
}
@Composable
fun DashedLine(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 10.dp,
    spaceLength: Dp = 5.dp,
    width: Dp = 200.dp,
    height: Dp = 2.dp
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(dashLength.toPx(), spaceLength.toPx())
            )
            drawLine(
                color = color,
                start = Offset(0f, height.toPx() / 2f),
                end = Offset(width.toPx(), height.toPx() / 2f),
                strokeWidth = strokeWidth.toPx(),
                pathEffect = pathEffect
            )
        }
    }
}

@Composable
fun FlightDetailCard(flight: Flight) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column {
            FlightAirportCard(flight = flight)
            HorizontalDivider(
                thickness = 2.dp, color = Color.Gray,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )
            TimeCard(flight = flight)
        }
    }
}

@Composable
fun FlightDetailCardList(flights: List<Flight>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
//        modifier = Modifier.padding(10.dp)
    ) {
        items(flights) { flight ->
            FlightDetailCard(flight = flight)
        }
    }
}

@Preview
@Composable
fun FlightDetailCardPreview() {
    FlightDetailCardList(flights = Flights.list)
}

