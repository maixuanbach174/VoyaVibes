package com.example.voyavibes.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar() {
    val openMinimalDialog = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("")}
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = {Text(text = "Search") },
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
                .padding(20.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.LightGray,
                focusedLabelColor = Color.DarkGray,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    openMinimalDialog.value = !openMinimalDialog.value
                    focusRequester.freeFocus()
                }
            )
    )
    when {
        openMinimalDialog.value -> {
            MinimalDialog(
                onDismissRequest = { openMinimalDialog.value = false },
                text = "Searching for $text"
            )
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar()
}