package com.lsbt.livesportsbettingtips.ui.screens.free

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.ui.theme.Prep
import com.lsbt.livesportsbettingtips.ui.theme.Primary

@Composable
fun FreeItem(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Prep),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .size(width = 358.dp, height = 107.dp)
            .background(color = Primary, shape = RoundedCornerShape(7.dp)),
            contentAlignment = Alignment.TopStart
            ){
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ){
               Spacer(modifier = Modifier.fillMaxWidth(0.05f))

                Column {
                    Text(
                        text = "Daily Sure Tips",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Last Updated 2 Weeks Ago",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.fillMaxWidth(0.05f))

                Image(painter = painterResource(id = R.drawable.vector_10_), contentDescription = "")

                Spacer(modifier = Modifier.fillMaxWidth(0.03f))

            }
        }
    }
}