package com.example.voyavibes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voyavibes.BackEnd.Seat
import com.example.voyavibes.BackEnd.SeatTable
import com.example.voyavibes.R
import com.example.voyavibes.uiComponents.TravellerSelectInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectSeatScreen(onBackClick: () -> Unit, onContinueClick: () -> Unit = {}) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0x65D3D3D3),
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        "Select Seats",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back to the Booking screen",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0x65D3D3D3))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 0.dp),
            ) {
                Text(
                    text = "Traveller",
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.size(10.dp))
                TravellerSelectInput()
                Spacer(modifier = Modifier.size(25.dp))
                SeatLabel()
                Spacer(modifier = Modifier.size(25.dp))
                DisplayHeadline()
                DisplaySeatTable(SeatTable.getSeats())
                Spacer(modifier = Modifier.size(25.dp))
                TotalSeatPanel(2, 200.0)
                Spacer(modifier = Modifier.size(25.dp))
                FilledTonalButton(
                    onClick = onContinueClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        contentColor = Color.White,
                        containerColor = colorResource(id = R.color.peach_300),
                    ),
                    content = {
                        Text(
                            text = "Continue",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                    },
                    shape = RoundedCornerShape(20.dp)
                )
            }
        }
    }
}

@Composable
fun SeatLabel(){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.peach_300),
                ),
                content = {
                },
                modifier = Modifier.size(25.dp),
                shape = RoundedCornerShape(6.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "Selected", style = MaterialTheme.typography.bodyMedium)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.green_700),
                ),
                content = {
                },
                modifier = Modifier.size(25.dp),
                shape = RoundedCornerShape(6.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "Booked", style = MaterialTheme.typography.bodyMedium)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.green_50),
                ),
                content = {
                },
                modifier = Modifier.size(25.dp),
                shape = RoundedCornerShape(6.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "Available", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun SeatTableSelect() {

}

@Composable
fun DisplaySeatTable(seats: MutableList<MutableList<Seat>>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.height(315.dp)
    ) {
        items(seats.size) {row->
            DisplaySeatInRow(rowSeats = seats[row], index = row + 1)
        }
    }
}

@Composable
fun DisplayHeadLine(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(text = "A", style = MaterialTheme.typography.headlineSmall)
        Text(text = "B", style = MaterialTheme.typography.headlineSmall)
        Box(modifier = Modifier.weight(1f))
        Text(text = "C", style = MaterialTheme.typography.headlineSmall)
        Text(text = "D", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun DisplayHeadline() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ){
                Text(text = "A",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ){
                Text(text = "B",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Box(modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        )
        {
            Text(text = "",
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ){
                Text(text = "C",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ){
                Text(text = "D",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun DisplaySeatInRow(rowSeats: MutableList<Seat>, index: Int){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            DisplaySeat(seat = rowSeats[0], modifier = Modifier
                .weight(1f)
                .size(55.dp))
            DisplaySeat(seat = rowSeats[1], modifier = Modifier
                .size(55.dp)
                .weight(1f))
        }
        Box(modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        )
        {
            Text(text = index.toString(),
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            DisplaySeat(seat = rowSeats[2], modifier = Modifier
                .size(55.dp)
                .weight(1f))
            DisplaySeat(seat = rowSeats[3], modifier = Modifier
                .size(55.dp)
                .weight(1f))
        }
    }
}

@Composable
fun DisplaySeat(seat:Seat, modifier: Modifier){
    Card (
        colors = CardDefaults.cardColors(
            containerColor = when (seat.status) {
                0 -> colorResource(id = R.color.peach_300)
                1 -> colorResource(id = R.color.green_700)
                else -> colorResource(id = R.color.green_50)
            },
        ),
        content = {

        },
        modifier = modifier,
        shape = RoundedCornerShape(13.dp)
    )
}

@Composable
fun TotalSeatPanel(seatNum: Int, totalPrice: Double){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(
                text = "Your Seats",
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.green_700),
                fontWeight = FontWeight.Bold
                )
            Text(
                text = seatNum.toString(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(
                text = "Total Price",
                style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.green_700),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$$totalPrice",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

//@Preview
//@Composable
//fun SeatTablePreview() {
//    val seats = SeatTable.getSeats()
//    DisplaySeatTable(seats)
//}


@Preview
@Composable
fun SelectSeatScreenPreview() {
    SelectSeatScreen(onBackClick = {})
}

//@Preview
//@Composable
//fun PreviewDisplayHeadline() {
//    DisplayHeadline()
//}

//@Preview
//@Composable
//fun SeatLabelPreview() {
//    SeatLabel()
//}