package com.lsbt.livesportsbettingtips.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsbt.livesportsbettingtips.ui.screens.destinations.ChatDestination
import com.lsbt.livesportsbettingtips.ui.theme.CardColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import java.text.DateFormat

@Destination
@Composable
fun Conversations(navigator: DestinationsNavigator) {
    val viewModel: ChatViewModel = koinViewModel()
    LaunchedEffect(key1 = Unit) {
        viewModel.getConversations()
    }
    val conversations = viewModel.conversations.observeAsState(listOf()).value

    LazyColumn {
        items(
            items = conversations
        ) {
            Row(
                Modifier
                    .clickable {
                        navigator.navigate(ChatDestination(chatId = it.key, isAdmin = true))
                    }
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .fillMaxWidth()
                    .background(CardColor, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp)),
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = it.name,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = it.lastMessage,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = DateFormat.getDateTimeInstance().format(it.lastUpdated),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}