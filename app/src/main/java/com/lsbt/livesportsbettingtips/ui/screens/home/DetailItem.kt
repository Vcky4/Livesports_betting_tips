package com.lsbt.livesportsbettingtips.ui.screens.home

import android.text.format.DateUtils
import androidx.compose.foundation.Image
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
fun DetailItem(item: TipModel, isHistory: Boolean = false, onClick: () -> Unit = {}) {
    val context = LocalContext.current
    //format date to DD/MM/YYYY
    val time = DateUtils.formatDateTime(
        context,
        item.date,
        if (isHistory) DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_24HOUR
        else DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_24HOUR
    )


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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outlined_flag),
                        contentDescription = "flag",
                        tint = Primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.league, fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = time, fontSize = 12.sp,
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
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.home, fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextDeep,
                        modifier = Modifier.weight(0.5f),
//                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                        ) {
                            Text(
                                text = item.homeScore, fontSize = 14.sp,
                                color = TextDeep,
                                fontWeight = FontWeight.Bold,
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Image(
                                painter = painterResource(
                                    id = when (item.status) {
                                        "won" -> R.drawable.check_circle
                                        "lost" -> R.drawable.cancel_fill
                                        else -> R.drawable.outlined_flag
                                    }
                                ),
                                contentDescription = "flag",
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = item.awayScore, fontSize = 14.sp,
                                color = TextDeep,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                        ) {
                            Text(
                                text = item.halfScore, fontSize = 15.sp,
                                color = TextDeep,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                    Text(
                        text = item.away, fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextDeep,
                        modifier = Modifier.weight(0.5f),
                        textAlign = TextAlign.End,
//                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = CardColor,
                        ),
                        modifier = Modifier,
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = item.prediction, fontSize = 13.sp,
                            color = TextDeep,
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 6.dp)
                        )
                    }
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = CardColor2,
                        ),
                        modifier = Modifier,
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = item.odd, fontSize = 13.sp,
                            color = TextDeep,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 6.dp)
                        )
                    }
                }
            }
        }
    }
}