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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderPositions
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voyavibes.R
import com.example.voyavibes.uiComponents.CustomToggleIconButtonList
import com.example.voyavibes.uiComponents.SortByComposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(onBackClick : ()->Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val selectedDepartureTime = remember { mutableIntStateOf(0) }
    val selectedArrivalTime = remember { mutableIntStateOf(1) }
    var sliderPosition by remember { mutableStateOf(50f..250f) }
    var beginPrice by remember { mutableStateOf("50") }
    var endPrice by remember { mutableStateOf("250") }

    // Update the text field values when the slider changes
    val onSliderValueChange: (ClosedFloatingPointRange<Float>) -> Unit = { range ->
        sliderPosition = range
        updatePriceRange(range, { beginPrice = it }, { endPrice = it })
    }

    // Update the slider values when the text fields change
    val onBeginPriceChangeFinished: () -> Unit = {
        if (isValidPrice(beginPrice)) {
            val updatedRange = updateSliderRange(beginPrice, endPrice, sliderPosition)
            sliderPosition = updatedRange
        } else {
            // Reset to previous valid value
            beginPrice = sliderPosition.start.toInt().toString()
        }
    }

    val onEndPriceChangeFinished: () -> Unit = {
        if (isValidPrice(endPrice)) {
            val updatedRange = updateSliderRange(beginPrice, endPrice, sliderPosition)
            sliderPosition = updatedRange
        } else {
            // Reset to previous valid value
            endPrice = sliderPosition.endInclusive.toInt().toString()
        }
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
                    .padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 0.dp)
                ,
            ) {
                Spacer(modifier = Modifier.size(5.dp))
                Text(text = "Departure", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                DepartureInput(selectedDepartureTime)
                Spacer(modifier = Modifier.size(25.dp))
                Text(text = "Arrival", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                DepartureInput(selectedArrivalTime)
                Spacer(modifier = Modifier.size(25.dp))
                Text(text = "Price", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                PriceSlider(
                    sliderPosition = sliderPosition,
                    onValueChange = onSliderValueChange
                )
                PriceInputField(
                    beginPrice = beginPrice,
                    endPrice = endPrice,
                    onBeginPriceChange = { beginPrice = it },
                    onEndPriceChange = { endPrice = it },
                    onBeginPriceChangeFinished = onBeginPriceChangeFinished,
                    onEndPriceChangeFinished = onEndPriceChangeFinished
                )
                Spacer(modifier = Modifier.size(25.dp))
                Text(text = "Facilities", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                CustomToggleIconButtonList()
                Spacer(modifier = Modifier.size(25.dp))
                Text(text = "Sort by", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                SortByComposable()
                ResetAndDoneButtons()
            }
        }
    }
}

@Composable
fun DepartureInput(departureTime: MutableIntState = mutableIntStateOf(0)){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(4){
            SelectButtonItem(
                onClick = {departureTime.intValue = it},
                isSelected = departureTime.intValue == it,
                text = when(it){
                    0 -> "12AM - 06AM"
                    1 -> "06AM - 12PM"
                    2 -> "12PM - 06PM"
                    3 -> "06PM - 12AM"
                    else -> ""
                }
            )
        }

    }


}

@Composable
fun SelectButtonItem(onClick: ()->Unit, isSelected: Boolean, text: String){
    val selectTextColor = Color.White
    val selectContainerColor = Color(0xFF089083)
    val unSelectTextColor = selectContainerColor
    val unSelectContainerColor = selectTextColor
    FilledTonalButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = if(isSelected) selectTextColor else unSelectTextColor,
            containerColor =  if(isSelected) selectContainerColor else unSelectContainerColor
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(
            text = text,
        )
    }
}

@Composable
fun PriceSlider(
    sliderPosition: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit
){
//    var sliderPosition by remember { mutableStateOf(50f..250f) }
    Column {
        RangeSlider(
            colors = SliderDefaults.colors(
                thumbColor = colorResource(id = R.color.green_700),
                disabledThumbColor = colorResource(id = R.color.green_700),
                activeTrackColor = colorResource(id = R.color.green_500),
                inactiveTrackColor = colorResource(id = R.color.green_50),
            ),
            value = sliderPosition,
            onValueChange = onValueChange,
            valueRange = 25f..500f,
            onValueChangeFinished = {
            },
        )
    }
}

@Composable
fun PriceInputField(
    beginPrice: String,
    endPrice: String,
    onBeginPriceChange: (String) -> Unit,
    onEndPriceChange: (String) -> Unit,
    onBeginPriceChangeFinished: () -> Unit,
    onEndPriceChangeFinished: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        OutlinedTextField(
            value = beginPrice,
            onValueChange = onBeginPriceChange,

            label = { Text("From")},
            leadingIcon = { Text("$", style = MaterialTheme.typography.bodyLarge) },
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF089083),
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color(0xFF089083),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),
            modifier = Modifier.weight(1f),
            textStyle = MaterialTheme.typography.bodyLarge,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onBeginPriceChangeFinished() }
            )

        )
        OutlinedTextField(
            value = endPrice,
            onValueChange = onEndPriceChange,
            label = { Text("To") },
            leadingIcon = { Text("$", style = MaterialTheme.typography.bodyLarge) },
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF089083),
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color(0xFF089083),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
            ),
            modifier = Modifier.weight(1f),
            textStyle = MaterialTheme.typography.bodyLarge,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onEndPriceChangeFinished() }
            )
        )
    }
}

@Composable
fun ResetAndDoneButtons(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    )
    {
        FilledTonalButton(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(id = R.color.peach_300),
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .weight(1f)
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = "Reset",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        FilledTonalButton(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = colorResource(id = R.color.peach_300)
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .weight(1f)
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = "Done",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}

fun updatePriceRange(
    range: ClosedFloatingPointRange<Float>,
    setBeginPrice: (String) -> Unit,
    setEndPrice: (String) -> Unit
) {
    setBeginPrice(range.start.toInt().toString())
    setEndPrice(range.endInclusive.toInt().toString())
}

fun updateSliderRange(
    beginPrice: String,
    endPrice: String,
    sliderPosition: ClosedFloatingPointRange<Float>
): ClosedFloatingPointRange<Float> {
    val begin = beginPrice.toFloatOrNull() ?: sliderPosition.start
    val end = endPrice.toFloatOrNull() ?: sliderPosition.endInclusive
    return if (begin in 25f..300f && end in 25f..300f && begin <= end) {
        begin..end
    } else {
        sliderPosition
    }
}

fun isValidPrice(value: String): Boolean {
    val price = value.toFloatOrNull()
    return price != null && price in 25f..300f
}

@Preview
@Composable
fun FilterScreenPreview(){
    FilterScreen(onBackClick = {})
}

@Preview
@Composable
fun PreviewResetDoneButton(){
    ResetAndDoneButtons()
}

