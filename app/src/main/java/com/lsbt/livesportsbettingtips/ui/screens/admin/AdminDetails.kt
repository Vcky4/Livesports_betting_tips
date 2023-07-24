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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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

@OptIn(ExperimentalMaterial3Api::class)
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
        AnimatedVisibility(visible = !editOpen) {
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
    }
    AnimatedVisibility(visible = editOpen) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                Modifier
                    .background(Secondary, shape = MaterialTheme.shapes.medium)
                    .fillMaxWidth(0.9f)
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = { editOpen = false },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cancel),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
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
                        .padding(vertical = 8.dp, horizontal = 14.dp)
                ) {
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
                                TextField(
                                    value = league,
                                    onValueChange = { league = it },
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent,
                                        errorIndicatorColor = Color.Transparent,
                                        cursorColor = TextDeep,
                                        textColor = TextDeep
                                    ),
                                    textStyle = TextStyle(fontSize = 18.sp),
                                    modifier = Modifier
                                        .fillMaxWidth(0.7f),
                                    placeholder = {
                                        Text(
                                            text = "League",
                                            fontSize = 18.sp,
                                            color = TextDeep.copy(alpha = 0.6f),
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    },
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
                                TextField(
                                    value = home,
                                    onValueChange = { home = it },
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent,
                                        errorIndicatorColor = Color.Transparent,
                                        cursorColor = TextDeep,
                                        textColor = TextDeep
                                    ),
                                    textStyle = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 18.sp
                                    ),
                                    modifier = Modifier
                                        .weight(0.3f),
                                    placeholder = {
                                        Text(
                                            text = "Home",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = TextDeep.copy(alpha = 0.6f),
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    },
                                )
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    TextField(
                                        value = awayScore,
                                        onValueChange = { awayScore = it },
                                        colors = TextFieldDefaults.textFieldColors(
                                            containerColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            disabledIndicatorColor = Color.Transparent,
                                            errorIndicatorColor = Color.Transparent,
                                            cursorColor = TextDeep,
                                            textColor = TextDeep
                                        ),
                                        textStyle = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        ),
                                        modifier = Modifier
                                            .width(40.dp),
                                        placeholder = {
                                            Text(
                                                text = "0",
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = TextDeep.copy(alpha = 0.6f),
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        },
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.outlined_flag),
                                        contentDescription = "flag",
                                        tint = Primary
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    TextField(
                                        value = homeScore,
                                        onValueChange = { homeScore = it },
                                        colors = TextFieldDefaults.textFieldColors(
                                            containerColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            disabledIndicatorColor = Color.Transparent,
                                            errorIndicatorColor = Color.Transparent,
                                            cursorColor = TextDeep,
                                            textColor = TextDeep
                                        ),
                                        textStyle = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        ),
                                        modifier = Modifier
                                            .width(40.dp),
                                        placeholder = {
                                            Text(
                                                text = "0",
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = TextDeep.copy(alpha = 0.6f),
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        },
                                    )

                                }
                                TextField(
                                    value = away,
                                    onValueChange = { away = it },
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent,
                                        errorIndicatorColor = Color.Transparent,
                                        cursorColor = TextDeep,
                                        textColor = TextDeep
                                    ),
                                    textStyle = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.End
                                    ),
                                    modifier = Modifier
                                        .weight(0.3f),
                                    placeholder = {
                                        Text(
                                            text = "Away",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = TextDeep.copy(alpha = 0.6f),
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    },
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
                                    TextField(
                                        value = prediction,
                                        onValueChange = { prediction = it },
                                        colors = TextFieldDefaults.textFieldColors(
                                            containerColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            disabledIndicatorColor = Color.Transparent,
                                            errorIndicatorColor = Color.Transparent,
                                            cursorColor = TextDeep,
                                            textColor = TextDeep
                                        ),
                                        textStyle = TextStyle(
                                            fontSize = 18.sp,
                                        ),
                                        modifier = Modifier.widthIn(max =100.dp),
                                        placeholder = {
                                            Text(
                                                text = "prediction",
                                                fontSize = 18.sp,
                                                color = TextDeep.copy(alpha = 0.6f),
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        },
                                    )
                                }
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = CardColor2,
                                    )
                                ) {
                                    TextField(
                                        value = odd,
                                        onValueChange = { odd = it },
                                        colors = TextFieldDefaults.textFieldColors(
                                            containerColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            disabledIndicatorColor = Color.Transparent,
                                            errorIndicatorColor = Color.Transparent,
                                            cursorColor = TextDeep,
                                            textColor = TextDeep
                                        ),
                                        textStyle = TextStyle(
                                            fontSize = 18.sp,
                                            textAlign = TextAlign.Center
                                        ),
                                        modifier = Modifier.widthIn(max =80.dp),
                                        placeholder = {
                                            Text(
                                                text = "odd",
                                                fontSize = 18.sp,
                                                color = TextDeep.copy(alpha = 0.6f),
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { editOpen = false },
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