package com.lsbt.livesportsbettingtips.ui.screens.chat

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsbt.livesportsbettingtips.ui.theme.CardColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

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
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .fillMaxWidth()
                    .background(CardColor, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
            ) {
                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = it.name,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    Text(text = it.lastMessage)
                }
                Text(text = it.lastMessage)
            }
        }
    }
}