package com.lsbt.livesportsbettingtips.ui.screens.notification

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun Notifications(navigator: DestinationsNavigator) {
    LazyColumn {
        items(10) {
            NotificationItem()
        }
    }
}
