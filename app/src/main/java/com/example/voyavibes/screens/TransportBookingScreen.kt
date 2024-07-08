package com.example.voyavibes.screens

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
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
import com.example.voyavibes.View.MainViewModel
import com.example.voyavibes.ui.theme.LightColorScheme
import com.example.voyavibes.uiComponents.MinimalDialog
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransportBookingScreen(
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit
){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val textFrom = remember { mutableStateOf("Da Nang (DAD)")}
    val textTo = remember { mutableStateOf("Ho Chi Minh City (SGN)")}

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
            ) {
                LocationTextField(textFrom, textTo)
                Spacer(modifier = Modifier.padding(5.dp))
                DateInput()
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Passenger & Luggage",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
                QuantityInput()
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Class",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.padding(5.dp))
                ClassInput()
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Transport",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.padding(5.dp))
                TransportInput()
                Spacer(modifier = Modifier.padding(15.dp))
                FilledTonalButton(
                    onClick = { onSearchClick()},
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = colorResource(id = R.color.peach_300)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = "Search",
                        fontSize = 20.sp
                    )
                }

            }
        }

    }

}

@Composable
fun LocationTextField(
    textFrom: MutableState<String>,
    textTo: MutableState<String>
    ) {
    Box(
        contentAlignment = CenterEnd,
    ) {
        Column {
            OutlinedTextField(
                value = textFrom.value,
                onValueChange = {
                    textFrom.value = it
                },
                label = { Text("From") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedBorderColor = Color.White,
                    cursorColor = Color.Black
                ) ,
                shape = RoundedCornerShape(15.dp),
            )
            OutlinedTextField(
                value = textTo.value,
                onValueChange = {
                    textTo.value = it
                },
                label = { Text("To") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedBorderColor = Color.White,
                    cursorColor = Color.Black
                ) ,
                shape = RoundedCornerShape(15.dp),

            )
        }

        IconButton(
            onClick = {
                val temp = textFrom.value
                textFrom.value = textTo.value
                textTo.value = temp
            },
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

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(){
    var departure by remember { mutableStateOf("July 04, 2024")}
    var returnDate by remember { mutableStateOf("July 14, 2024")}
    val calendarState = rememberSheetState()
    val dateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
    var currentField by remember { mutableIntStateOf(0) }

    var departureDate by remember { mutableStateOf<LocalDate?>(LocalDate.parse(departure, dateFormatter)) }
    var returnDateValue by remember { mutableStateOf<LocalDate?>(LocalDate.parse(returnDate, dateFormatter)) }

    val openMinimalDialog = remember { mutableStateOf(false) }
    when {
        openMinimalDialog.value -> {
            MinimalDialog(
                onDismissRequest = { openMinimalDialog.value = false },
                text = "The return date must be after the departure date."
            )
        }
    }

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date{
                date ->
            val formattedDate = date.format(dateFormatter)
            if (currentField == 0) {
                departureDate = date
                departure = formattedDate
                if (returnDateValue != null && returnDateValue!!.isBefore(date)) {
                    returnDateValue = date.plusDays(1)
                    returnDate = returnDateValue!!.format(dateFormatter)
                }
            } else {
                if (date.isAfter(departureDate)) {
                    returnDateValue = date
                    returnDate = formattedDate
                } else {
                    openMinimalDialog.value = true
                }
            }
        }
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        OutlinedTextField(
            value = departure,
            onValueChange = {
                departure = it
            },
            label = { Text("Departure") },
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = Color.Black,
                disabledLabelColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledBorderColor = Color.White,
                cursorColor = Color.Black
            ) ,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .weight(1f)
                .clickable {
                    currentField = 0
                    calendarState.show()
                },
           enabled = false
        )
        OutlinedTextField(
            enabled = false,
            value = returnDate,
            onValueChange = {
                returnDate = it
            },
            label = { Text("Return") },
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = Color.Black,
                disabledLabelColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledBorderColor = Color.White,
                cursorColor = Color.Black
            ) ,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .weight(1f)
                .clickable {
                    currentField = 1
                    calendarState.show()
                },
        )

    }
}

@Composable
fun QuantityInput(){
    var passNum by remember { mutableStateOf("1")}
    var babyNum by remember { mutableStateOf("0")}
    var petNum by remember { mutableStateOf("0")}
    var luggageNum by remember { mutableStateOf("1")}
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ){
        TextField(
            value = passNum,
            textStyle = MaterialTheme.typography.titleLarge,
            onValueChange = {
                passNum = it
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.type_user),
                    contentDescription = "Passenger Icon",
//                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)

                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = Color.Black,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedTextColor = colorResource(id = R.color.green_500),
                unfocusedLeadingIconColor = Color.LightGray,
                focusedLeadingIconColor = colorResource(id = R.color.green_500),
                focusedIndicatorColor = colorResource(id = R.color.green_500)
            ),
            modifier = Modifier
                .weight(1f)
        )
        TextField(
            value = babyNum,
            textStyle = MaterialTheme.typography.titleLarge,
            onValueChange = {
                babyNum = it
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baby),
                    contentDescription = "Passenger Icon",
                    modifier = Modifier
                        .size(24.dp)

                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = Color.Black,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedTextColor = colorResource(id = R.color.green_500),
                unfocusedLeadingIconColor = Color.LightGray,
                focusedLeadingIconColor = colorResource(id = R.color.green_500),
                focusedIndicatorColor = colorResource(id = R.color.green_500)
            ),
            modifier = Modifier
                .weight(1f)
        )
        TextField(
            value = petNum,
            textStyle = MaterialTheme.typography.titleLarge,
            onValueChange = {
                petNum = it
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.pet),
                    contentDescription = "Passenger Icon",
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = Color.Black,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedTextColor = colorResource(id = R.color.green_500),
                unfocusedLeadingIconColor = Color.LightGray,
                focusedLeadingIconColor = colorResource(id = R.color.green_500),
                focusedIndicatorColor = colorResource(id = R.color.green_500)
            ),
            modifier = Modifier
                .weight(1f)
        )
        TextField(
            value = luggageNum,
            textStyle = MaterialTheme.typography.titleLarge,
            onValueChange = {
                luggageNum = it
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.luggage),
                    contentDescription = "Passenger Icon"
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = Color.Black,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedTextColor = colorResource(id = R.color.green_500),
                unfocusedLeadingIconColor = Color.LightGray,
                focusedLeadingIconColor = colorResource(id = R.color.green_500),
                focusedIndicatorColor = colorResource(id = R.color.green_500)
            ),
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun ClassInput(){
    var isBusiness by remember{ mutableStateOf(true) }
    val selectTextColor = Color.White
    val selectContainerColor = Color(0xFF089083)
    val unSelectTextColor = selectContainerColor
    val unSelectContainerColor = selectTextColor
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ){
        FilledTonalButton(
            onClick = {isBusiness = false},
            colors = ButtonDefaults.buttonColors(
                contentColor = if(isBusiness) unSelectTextColor else selectTextColor,
                containerColor =  if(isBusiness) unSelectContainerColor else selectContainerColor
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Economy",
            )
        }

        FilledTonalButton(
            onClick = {isBusiness = true},
            colors = ButtonDefaults.buttonColors(
                contentColor = if(isBusiness) selectTextColor else unSelectTextColor,
                containerColor =  if(isBusiness) selectContainerColor else unSelectContainerColor
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Business",
            )
        }
    }


}

@Composable
fun TransportInput(){
    var transportSelect by remember{ mutableIntStateOf(0) }
    val selectTextColor = Color.White
    val selectContainerColor = Color(0xFF089083)
    val unSelectTextColor = selectContainerColor
    val unSelectContainerColor = selectTextColor
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ){
        FilledTonalButton(
            onClick = {transportSelect = 0},
            colors = ButtonDefaults.buttonColors(
                contentColor = if(transportSelect == 0) selectTextColor else unSelectTextColor,
                containerColor =  if(transportSelect == 0) selectContainerColor else unSelectContainerColor
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.airplanetilt),
                contentDescription = "Plane Icon",
                tint = if(transportSelect == 0) selectTextColor else unSelectTextColor
            )
        }

        FilledTonalButton(
            onClick = {transportSelect = 1},
            colors = ButtonDefaults.buttonColors(
                contentColor = if(transportSelect == 1) selectTextColor else unSelectTextColor,
                containerColor =  if(transportSelect == 1) selectContainerColor else unSelectContainerColor
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.boat),
                contentDescription = "Boat Icon",
                tint = if(transportSelect == 1) selectTextColor else unSelectTextColor
            )
        }

        FilledTonalButton(
            onClick = {transportSelect = 2},
            colors = ButtonDefaults.buttonColors(
                contentColor = if(transportSelect == 2) selectTextColor else unSelectTextColor,
                containerColor =  if(transportSelect == 2) selectContainerColor else unSelectContainerColor
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bus),
                contentDescription = "Bus Icon",
                tint = if(transportSelect == 2) selectTextColor else unSelectTextColor
            )
        }

        FilledTonalButton(
            onClick = {transportSelect = 3},
            colors = ButtonDefaults.buttonColors(
                contentColor = if(transportSelect == 3) selectTextColor else unSelectTextColor,
                containerColor =  if(transportSelect == 3) selectContainerColor else unSelectContainerColor
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.train),
                contentDescription = "Train Icon",
                tint = if(transportSelect == 3) selectTextColor else unSelectTextColor
            )
        }
    }
}
