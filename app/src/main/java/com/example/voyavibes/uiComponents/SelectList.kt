package com.example.voyavibes.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyavibes.R

data class selectItem(
    val thu : String,
    val ngay: String,
)

object selectList {
    val list = listOf(
        selectItem("TH", "04"),
        selectItem("FR", "05"),
        selectItem("SA", "06"),
        selectItem("SU", "07"),
        selectItem("MO", "08"),
        selectItem("TU", "09"),
        selectItem("WE", "10"),
        selectItem("TH", "11"),
        selectItem("FR", "12"),
    )
}

@Composable
fun SelectButton(selectItem: selectItem, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) colorResource(id = R.color.peach_50) else Color.Transparent)
            .padding(7.dp)
        ,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = selectItem.thu)
            Text(text = selectItem.ngay, fontWeight = FontWeight.Bold)
        }
    }
}
@Composable
fun SelectButtonList(isSelected: MutableState<selectItem>) {
//    var isSelected by remember { mutableStateOf(selectList.list.first()) }
    LazyRow(
//        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(selectList.list) { item ->
            SelectButton(selectItem = item, isSelected.value == item) {
                isSelected.value = item
            }
        }
    }
}

@Composable
fun TravellerSelectInput(
    traveller: MutableIntState = mutableIntStateOf(0),
    travellerNum: Int = 6
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(travellerNum){
            TravellerSelectButtonItem(
                onClick = {traveller.intValue = it},
                isSelected = traveller.intValue == it,
                text = (it + 1).toString()
            )
        }

    }


}

@Composable
fun TravellerSelectButtonItem(onClick: ()->Unit, isSelected: Boolean, text: String){
    val selectContainerColor = colorResource(id = R.color.peach_50)
    val unSelectContainerColor = Color.Transparent
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable { onClick() }
            .clip(RoundedCornerShape(10.dp))
            .background(color = if (isSelected) selectContainerColor else unSelectContainerColor)
            .size(40.dp)
    ){
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun PreviewSelectButtonList() {
    SelectButtonList( remember { mutableStateOf(selectList.list.first()) })
}

@Preview
@Composable
fun PreviewTravellerSelectInput() {
    TravellerSelectInput()
}