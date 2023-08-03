package com.lsbt.livesportsbettingtips.ui.screens.notification

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.data.StaticData
import com.lsbt.livesportsbettingtips.ui.screens.admin.AdminViewModel
import com.lsbt.livesportsbettingtips.ui.theme.Primary
import com.lsbt.livesportsbettingtips.ui.theme.Secondary
import com.lsbt.livesportsbettingtips.ui.theme.TextDeep
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.json.JSONException
import org.json.JSONObject
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun Notifications(navigator: DestinationsNavigator) {
    val viewModel: AdminViewModel=  koinViewModel()
    var title by remember {
        mutableStateOf("")
    }
    var body by remember {
        mutableStateOf("")
    }
    val topic = "Tips" //topic has to match what the receiver subscribed to

    val notification = JSONObject()
    val notifcationBody = JSONObject()

    try {
        notifcationBody.put("title", title)
        notifcationBody.put("message", body)   //Enter your notification message
        notification.put("to", topic)
        notification.put("data", notifcationBody)
    } catch (e: JSONException) {
        Log.e("TAG", "notification: " + e.message)
    }

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
                text = stringResource(id = R.string.post_a_notification),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = TextDeep,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.please_enter_the_details),
                fontSize = 18.sp,
                color = Primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    cursorColor = Primary,
                    textColor = TextDeep
                ),
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.what_the_title),
                        fontSize = 18.sp,
                        color = TextDeep.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = body,
                onValueChange = { body = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    cursorColor = Primary,
                    textColor = TextDeep
                ),
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.body),
                        fontSize = 18.sp,
                        color = TextDeep.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { viewModel.sendNotification(notification) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = TextDeep,
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(horizontal = 26.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.post),
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

}
