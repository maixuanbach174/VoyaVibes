package com.example.voyavibes.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
        selectItem("TH", "02"),
        selectItem("FR", "03"),
        selectItem("SA", "04"),
        selectItem("SU", "05"),
        selectItem("MO", "06"),
        selectItem("TU", "07"),
        selectItem("WE", "08"),
        selectItem("TH", "09"),
        selectItem("FR", "10"),
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
fun SelectButtonList() {
    var isSelected by remember { mutableStateOf(selectList.list.first()) }
    LazyRow(
//        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(selectList.list) { item ->
            SelectButton(selectItem = item, isSelected == item) {
                isSelected = item
            }
        }
    }
}

@Preview
@Composable
fun PreviewSelectButtonList() {
    SelectButtonList()
}