package com.lsbt.livesportsbettingtips.ui.screens.admin

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.lsbt.livesportsbettingtips.ui.screens.destinations.AdminDetailScreenDestination
import com.lsbt.livesportsbettingtips.ui.screens.destinations.ConversationsDestination
import com.lsbt.livesportsbettingtips.ui.screens.home.HomeItem
import com.lsbt.livesportsbettingtips.ui.theme.Secondary
import com.lsbt.livesportsbettingtips.ui.theme.TextDeep
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun Admin(navigator: DestinationsNavigator) {
    val viewModel: AdminViewModel = koinViewModel()
    val token = viewModel.token.observeAsState(initial = "").value
    val freeItems = viewModel.freeItems
    val vipItem = viewModel.vipItems
    val contactItems = viewModel.contactItems
    val context = LocalContext.current
    var contactTrigger by remember {
        mutableStateOf("")
    }
    var contact by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var processing by remember {
        mutableStateOf(false)
    }
    val whatsapp = viewModel.whatsApp.observeAsState().value
    val telegram = viewModel.telegram.observeAsState().value
    val email = viewModel.email.observeAsState().value

    LaunchedEffect(key1 = contactTrigger) {
        contact = when (contactTrigger) {
            "WhatsApp" -> TextFieldValue(whatsapp ?: "")
            "Email" -> TextFieldValue(email ?: "")
            else -> TextFieldValue(telegram ?: "")
        }
    }


    AnimatedVisibility(visible = token.isNotEmpty() && contactTrigger.isEmpty()) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.free),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.heightIn(max = 200.dp)
            ) {
                items(
                    items = freeItems,
                ) {
                    val title = when (it.title) {
                        R.string.dialy_sure_tips -> "Daily Sure Tips"
                        R.string.football_tips -> "Football tips"
                        R.string.basketball_tips -> "Basketball tips"
                        else -> "Tennis tips"
                    }
                    HomeItem(it) {
                        navigator.navigate(
                            AdminDetailScreenDestination(
                                title
                            )
                        )
                    }
                }
            }
            Text(
                text = stringResource(id = R.string.vip),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.heightIn(max = 200.dp)
            ) {
                items(
                    items = vipItem.filter {
                        it.title == R.string.previous_correct_score
                                || it.title == R.string.previous_draws_results
                    },
                ) {
                    val title = when (it.title) {
                        R.string.previous_draws_results -> "Previous Draws Results"
                        else -> "Previous Correct Score"
                    }
                    HomeItem(it) {
                        navigator.navigate(
                            AdminDetailScreenDestination(
                                title
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.contact_us),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.heightIn(max = 200.dp)
            ) {
                items(
                    items = contactItems,
                ) {
                    HomeItem(it) {
                        if (it.id == 8) {
                            navigator.navigate(ConversationsDestination())
                        } else {
                            contactTrigger = when (it.id) {
                                5 -> "WhatsApp"
                                6 -> "Email"
                                else -> "Telegram"
                            }
                        }
                    }
                }
            }
        }
    }

    AnimatedVisibility(visible = contactTrigger.isNotEmpty()) {
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
                    onClick = { contactTrigger = "" },
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
                    text = stringResource(id = R.string.click_field_to),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDeep,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Column {
                    Text(
                        text = contactTrigger,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDeep,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = contact,
                        onValueChange = { contact = it },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            cursorColor = TextDeep,
                            textColor = TextDeep
                        ),
                        shape = RoundedCornerShape(8.dp),
                        textStyle = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 20.dp),
                        placeholder = {
                            Text(
                                text = when (contactTrigger) {
                                    "WhatsApp" -> stringResource(id = R.string.enter_whatsapp_number)
                                    "Email" -> stringResource(id = R.string.enter_email_address)
                                    else -> stringResource(id = R.string.enter_telegram_username)
                                },
                                fontSize = 18.sp,
                                color = TextDeep.copy(alpha = 0.6f),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            processing = true
                            when (contactTrigger) {
                                "WhatsApp" -> viewModel.setWhatsApp(contact.text)
                                    .addOnSuccessListener {
                                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                                        processing = false
                                        contactTrigger = ""
                                    }.addOnFailureListener {
                                        Toast.makeText(
                                            context,
                                            "Failed: ${it.localizedMessage}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        processing = false
                                        contactTrigger = ""
                                    }

                                "Email" -> viewModel.setEmail(contact.text).addOnSuccessListener {
                                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                                    processing = false
                                    contactTrigger = ""
                                }.addOnFailureListener {
                                    Toast.makeText(
                                        context,
                                        "Failed: ${it.localizedMessage}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    processing = false
                                    contactTrigger = ""
                                }

                                else -> viewModel.setTelegram(contact.text).addOnSuccessListener {
                                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                                    processing = false
                                    contactTrigger = ""
                                }.addOnFailureListener {
                                    Toast.makeText(
                                        context,
                                        "Failed: ${it.localizedMessage}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    processing = false
                                    contactTrigger = ""
                                }
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
                                fontSize = 18.sp,
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

    if (token.isEmpty()) {
        Login(viewModel)
    }
}