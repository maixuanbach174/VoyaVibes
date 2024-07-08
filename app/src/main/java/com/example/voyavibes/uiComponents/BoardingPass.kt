package com.example.voyavibes.uiComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voyavibes.Data.Flight
import com.example.voyavibes.Data.Flights
import com.example.voyavibes.R

@Composable
fun BoardingPass(flight: Flight, seat: String = "") {
    Box(
        modifier = Modifier.clip(shape = RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            BoardingPassHeading(airwayName = "Vietnam Airlines")
            Divider(
                thickness = 2.dp, color = Color.Gray,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )
            FlightDetailCard(flight = flight, onCardClick = {})
            Divider(
                thickness = 2.dp, color = Color.Gray,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )
            TicketDetail(tiketId = "A-123", classType = "Economy", seat = seat, passengerNum = 1)
            BarCode()
            Text("A3427371903848")
            Spacer(modifier = Modifier.padding(10.dp))
        }

    }
}

@Composable
fun BoardingPassHeading(airwayName: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(15.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.property_1_plane_1_), contentDescription = "Plane")
            Text(text = airwayName, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun TicketDetail(
    tiketId: String,
    classType: String,
    seat:String,
    passengerNum: Int){
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
                    text = "Passenger",
                    color = colorResource(id = R.color.green_500),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = "$passengerNum.Adult",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Ticket",
                    color = colorResource(id = R.color.green_500),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = tiketId,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Class",
                    color = colorResource(id = R.color.green_500),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = classType,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Seat",
                    color = colorResource(id = R.color.green_500),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp)
                )
                Text(
                    text = seat,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                )
            }

        }
    }
}

@Composable
fun BarCode(){
    Row(modifier = Modifier.padding(10.dp)){
        for(i in 0..5)
            Image(painter = painterResource(id = R.drawable.barcode), contentDescription = "Barcode")
    }
}

@Preview
@Composable
fun BarCodeRV(){
    BarCode()
}
