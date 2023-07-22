package com.lsbt.livesportsbettingtips.ui.screens.free

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun Free(navigator: DestinationsNavigator) {
    val freeViewModel: FreeViewModel = koinViewModel()
    val freeItems = freeViewModel.freeItems.collectAsState(initial = listOf()).value
    LazyColumn {
        items(
            items = freeItems,
        ) {
            FreeItem(it)
        }
    }
}
