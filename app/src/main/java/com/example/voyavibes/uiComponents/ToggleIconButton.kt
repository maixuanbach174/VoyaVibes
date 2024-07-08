package com.example.voyavibes.uiComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyavibes.R

@Composable
fun CustomToggleIconButton(iconId: Int, checked: Boolean, onCheckedChange: (Boolean) -> Unit){
    FilledTonalIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        content = {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        },
        shape = RoundedCornerShape(15.dp),
        colors = IconButtonDefaults.filledIconToggleButtonColors(
            containerColor = Color.White,
            contentColor = colorResource(id = R.color.green_500),
            checkedContentColor = Color.White,
            checkedContainerColor = colorResource(id = R.color.green_500)
        ),
        modifier = Modifier.size(50.dp)
    )
}

@Composable
fun CustomToggleIconButtonList(
    coffeeChecked: MutableState<Boolean>,
    foodChecked: MutableState<Boolean>,
    wifiChecked: MutableState<Boolean>,
    coldChecked: MutableState<Boolean>
) {
//    val coffeeChecked = remember {
//        mutableStateOf(false)
//    }
//    val foodChecked = remember {
//        mutableStateOf(true)
//    }
//    val wifiChecked = remember {
//        mutableStateOf(true)
//    }
//    val coldChecked = remember {
//        mutableStateOf(false)
//    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.padding(top = 10.dp)
    ) {
        for (i in 0..3) {
            item {
                when (i) {
                    0 -> {
                        CustomToggleIconButton(
                            iconId = R.drawable.property_1_coffee,
                            checked = coffeeChecked.value,
                            onCheckedChange = {
                                coffeeChecked.value = it
                            }
                        )
                    }
                    1 -> {
                        CustomToggleIconButton(
                            iconId = R.drawable.property_1_forkknife,
                            checked = foodChecked.value,
                            onCheckedChange = {
                                foodChecked.value = it
                            }
                        )
                    }
                    2 -> {
                        CustomToggleIconButton(
                            iconId = R.drawable.property_1_wifihigh,
                            checked = wifiChecked.value,
                            onCheckedChange = {
                                wifiChecked.value = it
                            }
                        )
                    }
                    3 -> {
                        CustomToggleIconButton(
                            iconId = R.drawable.property_1_snowflake,
                            checked = coldChecked.value,
                            onCheckedChange = {
                                coldChecked.value = it
                            }
                        )
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun CustomToggleIconButtonListPreview() {
//    CustomToggleIconButtonList()
//}

