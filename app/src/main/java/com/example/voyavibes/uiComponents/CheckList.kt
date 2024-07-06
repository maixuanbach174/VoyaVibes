package com.example.voyavibes.uiComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyavibes.R

@Composable
fun CheckList(
    checkList: List<String>,
    selectedCheckIndex: MutableState<Int?>
) {
    LazyColumn(
        modifier = Modifier.height(100.dp)
    ) {
        items(checkList.size) { index ->
            CheckItem(
                checkItem = checkList[index],
                isSelected = selectedCheckIndex.value == index,
                onCheckedChange = { isSelected ->
                    if (isSelected) {
                        selectedCheckIndex.value = index
                    } else {
                        selectedCheckIndex.value = null
                    }
                }
            )
        }
    }
}

@Composable
fun CheckItem(
    checkItem: String,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(id = R.color.green_500),
                uncheckedColor = colorResource(id = R.color.green_500),
                checkmarkColor = Color.White
            )
        )
        Text(text = checkItem)
    }
}

@Composable
fun SortByComposable() {
    val checkList = listOf("Arrival Time", "Departure Time", "Price", "Duration")
    val selectedCheckIndex = remember { mutableStateOf<Int?>(0) }

    CheckList(
        checkList = checkList,
        selectedCheckIndex = selectedCheckIndex
    )
}

@Preview
@Composable
fun CheckListPreview() {
    SortByComposable()
}