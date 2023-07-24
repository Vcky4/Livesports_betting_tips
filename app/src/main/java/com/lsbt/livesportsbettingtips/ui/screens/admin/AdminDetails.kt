package com.lsbt.livesportsbettingtips.ui.screens.admin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.data.StaticData
import com.lsbt.livesportsbettingtips.data.db.models.TipModel
import com.lsbt.livesportsbettingtips.ui.screens.home.DetailItem
import com.lsbt.livesportsbettingtips.ui.theme.CardColor
import com.lsbt.livesportsbettingtips.ui.theme.CardColor2
import com.lsbt.livesportsbettingtips.ui.theme.Primary
import com.lsbt.livesportsbettingtips.ui.theme.Secondary
import com.lsbt.livesportsbettingtips.ui.theme.TextDeep
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AdminDetailScreen(trigger: String, navigator: DestinationsNavigator) {
    var editOpen by remember {
        mutableStateOf(false)
    }
    var league by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var prediction by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var home by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var away by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var homeScore by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var awayScore by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var odd by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Column {
        Row(
            modifier = Modifier.padding(vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navigator.navigateUp() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "back",
                    tint = Color.White
                )
            }
            Text(
                text = trigger,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        LazyColumn {
            item {
                Text(
                    text = "Today",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            items(StaticData.tips) {
                DetailItem(it) {
                    //set values
                    league = TextFieldValue(it.league)
                    prediction = TextFieldValue(it.prediction)
                    home = TextFieldValue(it.home)
                    away = TextFieldValue(it.away)
                    homeScore = TextFieldValue(it.homeScore)
                    awayScore = TextFieldValue(it.awayScore)
                    odd = TextFieldValue(it.odd)
                    editOpen = true
                }
            }
            item {
                Text(
                    text = "History",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = "12/10/2021",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            items(StaticData.tips) {
                DetailItem(it) {
                    //set values
                    league = TextFieldValue(it.league)
                    prediction = TextFieldValue(it.prediction)
                    home = TextFieldValue(it.home)
                    away = TextFieldValue(it.away)
                    homeScore = TextFieldValue(it.homeScore)
                    awayScore = TextFieldValue(it.awayScore)
                    odd = TextFieldValue(it.odd)
                    editOpen = true
                }
            }
        }
    }
    AnimatedVisibility(visible = editOpen) {
        Popup(onDismissRequest = {
            editOpen = false
        }) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    Modifier
                        .background(Secondary, shape = MaterialTheme.shapes.medium)
                        .fillMaxWidth(0.9f)
                        .padding(vertical = 50.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Click field to edit",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDeep,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Card(
                        Modifier
                            .clickable { }
                            .padding(vertical = 8.dp, horizontal = 14.dp)) {
                        Column(
                            Modifier
                                .background(Color.White, RoundedCornerShape(8.dp))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .background(
                                        Secondary,
                                        RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                                    )
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
                                            modifier = Modifier.padding(
                                                vertical = 6.dp,
                                                horizontal = 14.dp
                                            )
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
                                            modifier = Modifier.padding(
                                                vertical = 6.dp,
                                                horizontal = 16.dp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TextDeep,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.padding(horizontal = 26.dp)
                    ) {
                        Text(
                            text = "Save",
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }

}