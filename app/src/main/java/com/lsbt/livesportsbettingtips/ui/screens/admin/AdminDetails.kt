package com.lsbt.livesportsbettingtips.ui.screens.admin

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.text.format.DateFormat
import android.text.format.DateUtils
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.ui.screens.home.DetailItem
import com.lsbt.livesportsbettingtips.ui.theme.Background
import com.lsbt.livesportsbettingtips.ui.theme.CardColor
import com.lsbt.livesportsbettingtips.ui.theme.CardColor2
import com.lsbt.livesportsbettingtips.ui.theme.Primary
import com.lsbt.livesportsbettingtips.ui.theme.Secondary
import com.lsbt.livesportsbettingtips.ui.theme.TextDeep
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AdminDetailScreen(trigger: String, navigator: DestinationsNavigator) {
    val viewModel: AdminViewModel = koinViewModel()
    LaunchedEffect(key1 = viewModel) {
        viewModel.getTips(trigger)
    }
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
    var halfScore by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var key by remember {
        mutableStateOf<String?>(null)
    }
    var processing by remember {
        mutableStateOf(false)
    }
    var status by remember {
        mutableStateOf("pending")
    }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

// Fetching current year, month and day
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    var selectedDateText by remember { mutableStateOf("$dayOfMonth/${month + 1}/$year") }

    val tips = viewModel.tips.observeAsState(listOf()).value
    val prev = stringResource(id = R.string.previous_correct_score)
    val prev2 = stringResource(id = R.string.previous_draws_results)
    val history = when (trigger) {
        prev -> tips.sortedByDescending { it.date }
        prev2 -> tips.sortedByDescending { it.date }
        else -> tips.filter { !DateUtils.isToday(it.date) }.sortedByDescending { it.date }
    }

    val datePicker = DatePickerDialog(
        context,
        AlertDialog.THEME_HOLO_DARK,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            selectedDateText = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        },
        year, month, dayOfMonth,

        )
    Box {
        Column {
            Row(
                modifier = Modifier.padding(vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navigator.navigateUp() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = stringResource(id = R.string.back),
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
                LazyColumn(Modifier.fillMaxSize()) {
                    item {
                        Text(
                            text = stringResource(id = R.string.today),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    if (tips.none { DateUtils.isToday(it.date) }) {
                        item {
                            Text(
                                text = stringResource(id = R.string.no_tips_available),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 30.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        items(tips.filter { DateUtils.isToday(it.date) }) {
                            DetailItem(it) {
                                //set values
                                key = it.id
                                league = TextFieldValue(it.league)
                                prediction = TextFieldValue(it.prediction)
                                home = TextFieldValue(it.home)
                                away = TextFieldValue(it.away)
                                homeScore = TextFieldValue(it.homeScore)
                                awayScore = TextFieldValue(it.awayScore)
                                odd = TextFieldValue(it.odd)
                                halfScore = TextFieldValue(it.halfScore)
                                status = it.status
                                editOpen = true
                                selectedDateText = DateUtils.formatDateTime(
                                    context,
                                    it.date,
                                    DateUtils.FORMAT_SHOW_DATE
                                )
                                datePicker.updateDate(
                                    DateFormat.format("yyyy", it.date).toString().toInt(),
                                    DateFormat.format("MM", it.date).toString().toInt(),
                                    DateFormat.format("dd", it.date).toString().toInt()
                                )
                            }
                        }
                    }
                    item {
                        Text(
                            text = stringResource(id = R.string.history),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    if (history.isEmpty()) {
                        item {
                            Text(
                                text = stringResource(id = R.string.no_tips_history_available),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 30.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        items(history) {
                            DetailItem(it) {
                                //set values
                                key = it.id
                                league = TextFieldValue(it.league)
                                prediction = TextFieldValue(it.prediction)
                                home = TextFieldValue(it.home)
                                away = TextFieldValue(it.away)
                                homeScore = TextFieldValue(it.homeScore)
                                awayScore = TextFieldValue(it.awayScore)
                                odd = TextFieldValue(it.odd)
                                halfScore = TextFieldValue(it.halfScore)
                                editOpen = true
                                selectedDateText = DateUtils.formatDateTime(
                                    context,
                                    it.date,
                                    DateUtils.FORMAT_SHOW_DATE
                                )
                                datePicker.updateDate(
                                    DateFormat.format("yyyy", it.date).toString().toInt(),
                                    DateFormat.format("MM", it.date).toString().toInt(),
                                    DateFormat.format("dd", it.date).toString().toInt()
                                )
                                status = it.status
                            }
                        }
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = !editOpen, modifier = Modifier
                .padding(end = 30.dp, bottom = 40.dp)
                .align(Alignment.BottomEnd)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        //set values
                        key = null
                        league = TextFieldValue("")
                        prediction = TextFieldValue("")
                        home = TextFieldValue("")
                        away = TextFieldValue("")
                        homeScore = TextFieldValue("")
                        awayScore = TextFieldValue("")
                        odd = TextFieldValue("")
                        halfScore = TextFieldValue("")
                        editOpen = true
                        selectedDateText = "$dayOfMonth/${month + 1}/$year"
                        datePicker.updateDate(year, month, dayOfMonth)
                        status = "pending"
                    }
                    .background(Primary, RoundedCornerShape(50))
                    .clip(RoundedCornerShape(50))
                    .padding(vertical = 14.dp, horizontal = 20.dp)
            ) {
                Text(
                    stringResource(id = R.string.add),
                    color = Background,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow_forward),
                    contentDescription = stringResource(id = R.string.add),
                    tint = Background
                )
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (key != null) {
                            IconButton(
                                onClick = {
                                    viewModel.deleteTip(trigger, key!!).addOnSuccessListener {
                                        Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT)
                                            .show()
                                        processing = false
                                        editOpen = false
                                    }.addOnFailureListener {
                                        Toast.makeText(
                                            context,
                                            "Failed: ${it.localizedMessage}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        processing = false
                                        editOpen = false
                                    }
                                },
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.delete),
                                    contentDescription = stringResource(id = R.string.delete),
                                    tint = Color.White
                                )
                            }
                        } else {
                            Spacer(modifier = Modifier.width(50.dp))
                        }
                        IconButton(
                            onClick = { editOpen = false },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cancel),
                                contentDescription = stringResource(id = R.string.cancel),
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = R.string.click_field_to),
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
                                        contentDescription = stringResource(id = R.string.flag),
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
                                        textStyle = TextStyle(
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Start
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth(0.7f),
                                        placeholder = {
                                            Text(
                                                text = stringResource(id = R.string.league),
                                                fontSize = 16.sp,
                                                color = TextDeep.copy(alpha = 0.6f),
                                                textAlign = TextAlign.Start,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        },
                                    )
                                }
                                Text(
                                    text = selectedDateText, fontSize = 16.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .clickable { datePicker.show() }
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
                                            fontSize = 16.sp
                                        ),
                                        modifier = Modifier
                                            .weight(0.35f),
                                        placeholder = {
                                            Text(
                                                text = stringResource(id = R.string.home),
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = TextDeep.copy(alpha = 0.6f),
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        },
                                    )
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
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
                                                    fontSize = 16.sp
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
                                            Image(
                                                painter = painterResource(
                                                    id = when (status) {
                                                        "won" -> R.drawable.check_circle
                                                        "lost" -> R.drawable.cancel_fill
                                                        else -> R.drawable.outlined_flag
                                                    }
                                                ),
                                                contentDescription = stringResource(id = R.string.flag),
                                                modifier = Modifier.clickable {
                                                    status = when (status) {
                                                        "won" -> "lost"
                                                        "lost" -> "pending"
                                                        else -> "won"
                                                    }
                                                }
                                            )
                                            Spacer(modifier = Modifier.width(5.dp))
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
                                                    fontSize = 16.sp,
                                                    textAlign = TextAlign.Center
                                                ),
                                                modifier = Modifier
                                                    .width(40.dp),
                                                placeholder = {
                                                    Text(
                                                        text = "0",
                                                        fontSize = 16.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = TextDeep.copy(alpha = 0.6f),
                                                        textAlign = TextAlign.Center,
                                                        modifier = Modifier.fillMaxWidth()
                                                    )
                                                },
                                            )

                                        }

                                        TextField(
                                            value = halfScore,
                                            onValueChange = { halfScore = it },
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
                                                fontSize = 16.sp
                                            ),
                                            modifier = Modifier.width(90.dp),
                                            placeholder = {
                                                Text(
                                                    text = "(0 - 0)",
                                                    fontSize = 16.sp,
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
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.End
                                        ),
                                        modifier = Modifier
                                            .weight(0.35f),
                                        placeholder = {
                                            Text(
                                                text = "Away",
                                                fontSize = 16.sp,
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
                                                fontSize = 16.sp,
                                            ),
                                            modifier = Modifier.widthIn(max = 120.dp),
                                            placeholder = {
                                                Text(
                                                    text = stringResource(id = R.string.prediction),
                                                    fontSize = 16.sp,
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
                                                fontSize = 16.sp,
                                                textAlign = TextAlign.Center
                                            ),
                                            modifier = Modifier.widthIn(max = 80.dp),
                                            placeholder = {
                                                Text(
                                                    text = stringResource(id = R.string.odd),
                                                    fontSize = 16.sp,
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
                        onClick = {
                            processing = true
                            val date = Calendar.getInstance()
                            date.set(
                                datePicker.datePicker.year,
                                datePicker.datePicker.month,
                                datePicker.datePicker.dayOfMonth
                            )
                            viewModel.saveTip(
                                trigger,
                                key,
                                league.text,
                                home.text,
                                away.text,
                                homeScore.text,
                                awayScore.text,
                                odd.text,
                                status,
                                prediction.text,
                               halfScore =  halfScore.text,
                                //convert selected date to timestamp
                                date = date.timeInMillis,
                            ).addOnSuccessListener {
                                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                                processing = false
                                editOpen = false
                            }.addOnFailureListener {
                                Toast.makeText(
                                    context,
                                    "Failed: ${it.localizedMessage}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                processing = false
                                editOpen = false
                            }

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TextDeep,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.padding(horizontal = 26.dp)
                    ) {
                        if (!processing) {
                            Text(
                                text = stringResource(id = R.string.save),
                                fontSize = 16.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        } else {
                            CircularProgressIndicator(color = Color.White)
                        }
                    }
                }
            }
        }
    }

}