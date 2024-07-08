package com.example.voyavibes.screens

import android.content.IntentSender.OnFinished
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voyavibes.R
import com.example.voyavibes.uiComponents.SuccessDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalScreen(onBackClick: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val firstName = remember { mutableStateOf("Elie") }
    val lastName = remember{ mutableStateOf("Doe") }
    val phone = remember{ mutableStateOf("+84 905715246") }
    val email = remember{ mutableStateOf("eliedoe246@gmail.com")}

    val openSuccessDialog = remember { mutableStateOf(false) }

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
                        "Personal Information",
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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 80.dp),
            ) {
                AvatarEdit(){
                }
                Spacer(modifier = Modifier.padding(20.dp))
                EditInfo(
                    textList = listOf(firstName.value, lastName.value, phone.value, email.value),
                    onValueChangeList = listOf(
                        {firstName.value = it},
                        {lastName.value = it},
                        {phone.value = it},
                        {email.value = it}
                    )
                )

                Spacer(modifier = Modifier.weight(1f))
                FilledTonalButton(
                    onClick = {
                        openSuccessDialog.value = !openSuccessDialog.value
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.peach_300),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Save changes",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(7.dp),
                        color = Color.White
                    )

                }

            }
        }
        when {
            openSuccessDialog.value -> {
                SuccessDialog {
                    openSuccessDialog.value = false
                }
            }
        }

    }
}

@Composable
fun AvatarEdit(onClick: ()->Unit){
    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.BottomEnd
    )
    {
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "avatar",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(BorderStroke(1.dp, Color.Black))
                .background(Color.Yellow),
            contentScale = ContentScale.Crop
        )
        IconButton(
            onClick = onClick,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.property_1_add_photo_camera),
                contentDescription = "Edit",
                tint = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun PersonalScreenPreview() {
    AvatarEdit(){}
}

@Composable
fun EditInfo(textList: List<String>,onValueChangeList: List<(String)->Unit>){
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        TextFieldItem(
            text = textList[0],
            label = "First Name",
            onValueChange = onValueChangeList[0],
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextFieldItem(
            text = textList[1],
            label = "Last Name",
            onValueChange = onValueChangeList[1],
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextFieldItem(
            text = textList[2],
            label = "Phone",
            onValueChange = onValueChangeList[2],
        )
        Spacer(modifier = Modifier.size(10.dp))
        TextFieldItem(
            text = textList[3],
            label = "Email",
            onValueChange = onValueChangeList[3],
        )
    }

}

@Composable
fun TextFieldItem(
    text: String,
    label: String,
    onValueChange: (String) -> Unit
){
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedContainerColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedContainerColor = colorResource(id = R.color.peach_50),
            focusedBorderColor = Color.White,
            cursorColor = Color.Black
        ) ,
        shape = RoundedCornerShape(15.dp),
        )
}

@Preview
@Composable
fun PreviewPersonalScreen(){
    PersonalScreen(onBackClick = {})
}
