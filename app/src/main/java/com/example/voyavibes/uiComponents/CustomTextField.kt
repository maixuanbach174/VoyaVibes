package com.example.voyavibes.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("")}
        TextField(
            value = text,
            onValueChange = { text = it },
            label = {Text(text = "Search", fontSize = 20.sp) },
            leadingIcon = { Icon(
                Icons.Filled.LocationOn,
                contentDescription = "Location Icon",
                tint = Color.Red,
                modifier = Modifier.size(40.dp)
            ) },
            trailingIcon = { Icon(
                Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0xFFFEA36B), RoundedCornerShape(15.dp))
                    .size(40.dp)
            ) },
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = Color.LightGray,
                focusedLabelColor = Color.DarkGray,
                unfocusedContainerColor = Color.White,
            )
    )
}

//@Composable
//fun QuantitySelectTextField(id: Int) {
//    var text by remember { mutableStateOf("")}
//    TextField(
//        value = text,
//        onValueChange = { text = it },
//        label = {Text(text = "Quantity", fontSize = 20.sp) },
//        leadingIcon = { Icon(
//            painter = painterResource(id = id),
//            contentDescription = "Clear Icon",
//            tint = Color.White,
//            modifier = Modifier.size(40.dp)
//        ) },
//        shape = RoundedCornerShape(25.dp),
//        modifier = Modifier
//            .fillMaxWidth(),
//        colors = TextFieldDefaults.colors(
//            unfocusedTextColor = Color.LightGray,
//            focusedLabelColor = Color.DarkGray,
//            unfocusedContainerColor = Color.Transparent,
//            focusedContainerColor = Color.Transparent,
//        )
//    )
//}


@Preview
@Composable
fun SearchBarPreview() {
    SearchBar()
}