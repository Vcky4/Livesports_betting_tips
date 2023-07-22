package com.lsbt.livesportsbettingtips.ui.screens.free

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.lsbt.livesportsbettingtips.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun Free(navigator: DestinationsNavigator) {
    LazyColumn {
        items(
            items = freeItems,
        ) {
            FreeItem(it)
        }
    }
}


data class FreeModel(
    val id: Int,
    val title: String,
    val description: String,
    val date: Long = System.currentTimeMillis(),
    val image: Int
)

val freeItems = listOf(
    FreeModel(
        id = 1,
        title = "Daily Sure Tips",
        description = "Last Updated 2 Weeks Ago",
        image = R.drawable.tips_and_updates
    ),
    FreeModel(
        id = 2,
        title = "Football Tips",
        description = "Last Updated 2 Weeks Ago",
        image = R.drawable.sports_football
    ),
    FreeModel(
        id = 3,
        title = "Basketball Tips",
        description = "Last Updated 2 Weeks Ago",
        image = R.drawable.sports_basketball
    ),
    FreeModel(
        id = 4,
        title = "Tennis Tips",
        description = "Last Updated 2 Weeks Ago",
        image = R.drawable.sports_tennis
    ),
    FreeModel(
        id = 5,
        title = "Live Score",
        description = "Last Updated 2 Weeks Ago",
        image = R.drawable.sports_score
    ),
    FreeModel(
        id = 6,
        title = "Live Match",
        description = "Last Updated 2 Weeks Ago",
        image = R.drawable.live_tv
    ),
)
