package com.lsbt.livesportsbettingtips.data

import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.data.db.models.FreeModel

object StaticData {
    val freeItems =   listOf(
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