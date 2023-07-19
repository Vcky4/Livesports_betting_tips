package com.lsbt.livesportsbettingtips.ui.screens.free

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun Free(navigator: DestinationsNavigator) {
    LazyColumn {
        items(10) {
            FreeItem()
        }
    }
}