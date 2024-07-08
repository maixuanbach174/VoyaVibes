package com.example.voyavibes.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.voyavibes.uiComponents.MinimalDialog
import com.example.voyavibes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(innerPadding:PaddingValues, onDetailClick: ()->Unit = {}) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val openMinimalDialog = remember { mutableStateOf(false) }
    val onClickOpts = listOf(
        { onDetailClick()},
        {openMinimalDialog.value = !openMinimalDialog.value},
        { openMinimalDialog.value = !openMinimalDialog.value },
        { openMinimalDialog.value = !openMinimalDialog.value },
        { openMinimalDialog.value = !openMinimalDialog.value },
    )
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
                        "Account",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) {innerPadding2 ->
        Box(
            modifier = Modifier
                .padding(innerPadding2)
                .fillMaxSize()
                .background(Color(0x65D3D3D3))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 80.dp),
            ) {
                Avatar()
                Spacer(modifier = Modifier.padding(20.dp))
                AccountManageOption(onClickOpts)
                Spacer(modifier = Modifier.weight(1f))
                FilledTonalButton(
                    onClick = {
                        openMinimalDialog.value = !openMinimalDialog.value
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Red
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.property_1_logout),
                        contentDescription = "icon_user",
                        tint = Color.Red
                    )
                    Text(
                        text = "End session",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(7.dp),
                        color = Color.Red
                    )
                }
            }
        }
    }
    when {
        openMinimalDialog.value -> {
            MinimalDialog(
                onDismissRequest = { openMinimalDialog.value = false },
                text = "This feature is developing"
            )
        }
    }
}

@Composable
fun Avatar() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
        Text(
            text = "Elie Doe",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

    }
}

@Composable
fun AccountManageOption(onClickOpts: List<()->Unit> = listOf()){
    Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        AccountManageOptionItem("Personal information", R.drawable.type_user, onClickOpts[0])
        AccountManageOptionItem("Payment and cards", R.drawable.property_1_payment_card, onClickOpts[1])
        AccountManageOptionItem("Saved", R.drawable.property_1_heartstraight, onClickOpts[2])
        AccountManageOptionItem("Booking history", R.drawable.property_1_history, onClickOpts[3])
        AccountManageOptionItem("Settings", R.drawable.property_1_settings_2, onClickOpts[4])
    }
}

@Composable
fun AccountManageOptionItem(text: String, id: Int, onCLick: ()->Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onCLick()}
    ) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = "icon_user",
            tint = colorResource(id = R.color.peach_300)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(PaddingValues(0.dp), {})
}

//@Preview
//@Composable
//fun AvatarPreview() {
//    Avatar()
//}

//@Preview
//@Composable
//fun AccountManageOptionPreview() {
//    AccountManageOption()
//}
