package com.lsbt.livesportsbettingtips.ui.screens.home

import android.text.format.DateUtils
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.data.db.models.TipModel
import com.lsbt.livesportsbettingtips.ui.theme.CardColor
import com.lsbt.livesportsbettingtips.ui.theme.CardColor2
import com.lsbt.livesportsbettingtips.ui.theme.Primary
import com.lsbt.livesportsbettingtips.ui.theme.Secondary
import com.lsbt.livesportsbettingtips.ui.theme.TextDeep

@Composable
fun DetailItem(item: TipModel, onClick: () -> Unit = {}) {
    //convert date to time and format it HH:mm
    val context = LocalContext.current
    val time = DateUtils.formatDateTime(
        context,
        item.date,
        DateUtils.FORMAT_SHOW_DATE
    )

    Card(
        Modifier
            .clickable { onClick.invoke() }
            .padding(vertical = 8.dp, horizontal = 14.dp)) {
        Column(
            Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
        ) {
            Text(
                text = time, fontSize = 18.sp,
                color = Secondary,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 14.dp)
                    .align(Alignment.End),
            )
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
                        text = item.league, fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(0.9f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
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
                        text = item.home, fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextDeep,
                        modifier = Modifier.weight(0.3f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.homeScore, fontSize = 18.sp,
                            color = TextDeep,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            painter = painterResource(
                                id = when (item.status) {
                                    "won" -> R.drawable.check_circle
                                    "lost" -> R.drawable.cancel_fill
                                    else -> R.drawable.outlined_flag
                                }
                            ),
                            contentDescription = "flag",
                            tint = Primary
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = item.awayScore, fontSize = 18.sp,
                            color = TextDeep,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Text(
                        text = item.away, fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextDeep,
                        modifier = Modifier.weight(0.3f),
                        textAlign = TextAlign.End,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
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
                            text = item.prediction, fontSize = 16.sp,
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
                            text = item.odd, fontSize = 16.sp,
                            color = TextDeep,
                            modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}