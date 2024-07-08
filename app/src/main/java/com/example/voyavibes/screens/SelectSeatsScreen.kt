package com.example.voyavibes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.voyavibes.uiComponents.MinimalDialog
import com.example.voyavibes.uiComponents.TravellerSelectInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectSeatScreen(onBackClick: () -> Unit, onContinueClick: (String) -> Unit = {}) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val selectPosition = remember { mutableStateOf(Pair(-1, -1)) }
    val openMinimalDialog = remember { mutableStateOf(false) }
    val travellerNum = remember {mutableIntStateOf(0)}
    val totalPrice = remember { mutableDoubleStateOf(0.0)}
    val seat = remember { mutableStateOf("None")}

    seat.value = if(selectPosition.value.first != -1 && selectPosition.value.second != -1) {
        SeatTable.getSeats()[selectPosition.value.first][selectPosition.value.second].seat
    } else {
        "None"
    }

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
                DisplaySeatTable(SeatTable.getSeats(), onClick = {
                    if(isAvailable(SeatTable.getSeats()[it.first][it.second])){
                        if(selectPosition.value.first != -1 && selectPosition.value.second != -1) {
                            SeatTable.getSeats()[selectPosition.value.first][selectPosition.value.second].status.intValue = 2
                        } else
                        {
                            totalPrice.doubleValue += SeatTable.getSeats()[it.first][it.second].price
                        }
                        selectPosition.value = it
                        SeatTable.getSeats()[it.first][it.second].status.intValue = 0
                    } else
                    {
                        openMinimalDialog.value = true
                    }
                })
                Spacer(modifier = Modifier.size(25.dp))
                TotalSeatPanel( seat.value, totalPrice.doubleValue, travellerNum.intValue + 1)
                Spacer(modifier = Modifier.size(25.dp))
                FilledTonalButton(
                    onClick = {
                        onContinueClick(seat.value)
                    },
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
    when {
        openMinimalDialog.value -> {
            MinimalDialog(
                onDismissRequest = { openMinimalDialog.value = false },
                text = "This seat is booked."
            )
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

fun isAvailable(seat: Seat): Boolean {
    return seat.status.intValue == 2
}

@Composable
fun DisplaySeatTable(seats: MutableList<MutableList<Seat>>, onClick: (Pair<Int, Int>)->Unit = {}){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.height(315.dp)
    ) {
        items(seats.size) {row->
            DisplaySeatInRow(rowSeats = seats[row], index = row, onClick = onClick)
        }
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
fun DisplaySeatInRow(rowSeats: MutableList<Seat>, index: Int, onClick: (Pair<Int,Int>)->Unit = {}){
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
                .size(55.dp), onClick = onClick, index = Pair(index, 0))
            DisplaySeat(seat = rowSeats[1], modifier = Modifier
                .size(55.dp)
                .weight(1f), onClick = onClick, index = Pair(index, 1))
        }
        Box(modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        )
        {
            Text(text = (index+1).toString(),
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            DisplaySeat(seat = rowSeats[2], modifier = Modifier
                .size(55.dp)
                .weight(1f), onClick = onClick, index = Pair(index, 2))
            DisplaySeat(seat = rowSeats[3], modifier = Modifier
                .size(55.dp)
                .weight(1f), onClick = onClick, index = Pair(index, 3))
        }
    }
}

@Composable
fun DisplaySeat(seat:Seat, modifier: Modifier, onClick: (Pair<Int,Int>)->Unit, index: Pair<Int, Int>){
    Card (
        colors = CardDefaults.cardColors(
            containerColor = when (seat.status.intValue) {
                0 -> colorResource(id = R.color.peach_300)
                1 -> colorResource(id = R.color.green_700)
                else -> colorResource(id = R.color.green_50)
            },
        ),
        content = {

        },
        modifier = modifier.clickable { onClick(index) },
        shape = RoundedCornerShape(13.dp)
    )
}

@Composable
fun TotalSeatPanel(seatNum: String, totalPrice: Double, travellerNum: Int = 0){
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
                text = "Traveller $travellerNum/ Seat $seatNum",
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