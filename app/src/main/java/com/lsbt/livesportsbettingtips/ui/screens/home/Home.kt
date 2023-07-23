package com.lsbt.livesportsbettingtips.ui.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.lsbt.livesportsbettingtips.ui.screens.free.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun Home(navigator: DestinationsNavigator) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val freeItems = homeViewModel.freeItems.collectAsState(initial = listOf()).value
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(
            items = freeItems,
        ) {
            HomeItem(it)
        }
    }
}
