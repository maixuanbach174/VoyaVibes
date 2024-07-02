package com.example.voyavibes.screens

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voyavibes.R
import com.example.voyavibes.ui.theme.LightColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransportBookingScreen(onBackClick: () -> Unit){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var fromText by remember{ mutableStateOf("")}
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
                        "Transport Booking",
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
        },
            ){
        innerPadding ->
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LocationTextField()
                Spacer(modifier = Modifier.padding(5.dp))
                DateInput()

            }
        }

    }

}

@Composable
fun LocationTextField() {
    Box(
        contentAlignment = CenterEnd,
    ) {
        Column {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("From") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.LightGray,
                    focusedLabelColor = Color.Black,
                    unfocusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedContainerColor = colorResource(id = R.color.peach_50),
                    focusedBorderColor = Color.White
                ) ,
                shape = RoundedCornerShape(15.dp),
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("To") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.LightGray,
                    focusedLabelColor = Color.Black,
                    unfocusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedContainerColor = colorResource(id = R.color.peach_50),
                    focusedBorderColor = Color.White
                ) ,
                shape = RoundedCornerShape(15.dp),

            )
        }

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .offset(x = (-30).dp, y = 5.dp)
                .background(Color(0xFFFEA36B), RoundedCornerShape(10.dp))
                .size(35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_vertical),
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier
                    .size(20.dp)
            )
        }

    }
}

@Composable
fun DateInput(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Departure") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.LightGray,
                focusedLabelColor = Color.Black,
                unfocusedContainerColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedContainerColor = colorResource(id = R.color.peach_50),
                focusedBorderColor = Color.White
            ) ,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Return") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.LightGray,
                focusedLabelColor = Color.Black,
                unfocusedContainerColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedContainerColor = colorResource(id = R.color.peach_50),
                focusedBorderColor = Color.White
            ) ,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.weight(1f)
        )

    }
}

@Composable
fun QuantityInput(){

}

@Preview
@Composable
fun TransportBookingScreenPreview(){
    TransportBookingScreen(onBackClick = {})
}

//@Preview
//@Composable
//fun LocationTextFieldPreview(){
//    LocationTextField()
//}
