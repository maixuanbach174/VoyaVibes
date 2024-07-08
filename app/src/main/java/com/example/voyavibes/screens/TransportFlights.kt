package com.example.voyavibes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voyavibes.Data.Flight
import com.example.voyavibes.Data.Flights
import com.example.voyavibes.R
import com.example.voyavibes.uiComponents.FlightDetailCardList
import com.example.voyavibes.uiComponents.SelectButtonList
import com.example.voyavibes.uiComponents.selectList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransportFlights(onBackClick: () -> Unit, onFilterClick: () -> Unit, onFlightClick: (Flight)->Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val isSelected = remember {
        mutableStateOf(selectList.list.first())
    }
    val numFlights = remember {
        mutableIntStateOf(9)
    }
    numFlights.intValue = when (isSelected.value.ngay) {
        "04" -> Flights.list_04_July.size
        "05" -> Flights.list_05_July.size
        "06" -> Flights.list_06_July.size
        else -> {
            Flights.list_04_July.size}
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
                        "Flights",
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
                SelectButtonList(isSelected)
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "${numFlights.intValue} flights available Da Nang to HCM City",
                    )
                    FilledTonalIconButton(
                        onClick = onFilterClick,
                        shape = RoundedCornerShape(10.dp),
                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                            contentColor = Color.White,
                            containerColor = colorResource(id = R.color.peach_300)
                        )
                    ) {
                        Icon(painter = painterResource(id = R.drawable.property_1_sliders_1) ,
                            contentDescription = "Filter flights",
                            tint = Color.White,
                        )
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                when (isSelected.value.ngay) {
                    "04" -> FlightDetailCardList(flights = Flights.list_04_July, onCardClick = onFlightClick)
                    "05" -> FlightDetailCardList(flights = Flights.list_05_July, onCardClick = onFlightClick)
                    "06" -> FlightDetailCardList(flights = Flights.list_06_July, onCardClick = onFlightClick)
                    else -> FlightDetailCardList(flights = Flights.list_04_July, onCardClick = onFlightClick)
                }
            }
        }
    }
}

@Preview
@Composable
fun TransportFlightsPreview() {
    TransportFlights(onBackClick = {}, onFilterClick = {}, onFlightClick = {})
}