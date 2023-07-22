package com.lsbt.livesportsbettingtips.ui.screens.free

import androidx.lifecycle.ViewModel
import com.lsbt.livesportsbettingtips.R
import org.koin.core.component.KoinComponent

class FreeViewModel : ViewModel(), KoinComponent {
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
}

data class FreeModel(
    val id: Int,
    val title: String,
    val description: String,
    val date: Long = System.currentTimeMillis(),
    val image: Int
)

