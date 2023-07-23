package com.lsbt.livesportsbettingtips.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.ui.theme.CardColor
import com.lsbt.livesportsbettingtips.ui.theme.CardColor2
import com.lsbt.livesportsbettingtips.ui.theme.Primary
import com.lsbt.livesportsbettingtips.ui.theme.Secondary
import com.lsbt.livesportsbettingtips.ui.theme.TextDeep

@Composable
fun DetailItem(onClick: () -> Unit = {}) {
    Card(
        Modifier
            .clickable { onClick.invoke() }
            .padding(vertical = 8.dp, horizontal = 14.dp)) {
        Column(
            Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .background(Secondary, RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.outlined_flag),
                        contentDescription = "flag",
                        tint = Primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Egypt Premier League", fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier
                    )
                }
                Text(
                    text = "21:30", fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier
                )
            }
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 14.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Cairo", fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextDeep,
                        modifier = Modifier.weight(0.3f),
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "0", fontSize = 18.sp,
                            color = TextDeep,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.outlined_flag),
                            contentDescription = "flag",
                            tint = Primary
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "1", fontSize = 18.sp,
                            color = TextDeep,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Text(
                        text = "Cairo", fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextDeep,
                        modifier = Modifier.weight(0.3f),
                        textAlign = TextAlign.End
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = CardColor,
                        )
                    ) {
                        Text(
                            text = "Over+ 1,5 Goals", fontSize = 16.sp,
                            color = TextDeep,
                            modifier = Modifier.padding(vertical = 6.dp, horizontal = 14.dp)
                        )
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = CardColor2,
                        )
                    ) {
                        Text(
                            text = "1.15", fontSize = 16.sp,
                            color = TextDeep,
                            modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}