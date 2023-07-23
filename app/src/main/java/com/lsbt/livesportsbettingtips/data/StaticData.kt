package com.lsbt.livesportsbettingtips.data

import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.data.db.models.HomeItemModel

object StaticData {
    val freeItems =   listOf(
        HomeItemModel(
            id = 1,
            title = "Daily Sure Tips",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.tips_and_updates
        ),
        HomeItemModel(
            id = 2,
            title = "Football Tips",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_football
        ),
        HomeItemModel(
            id = 3,
            title = "Basketball Tips",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_basketball
        ),
        HomeItemModel(
            id = 4,
            title = "Tennis Tips",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_tennis
        ),
        HomeItemModel(
            id = 5,
            title = "Live Score",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_score
        ),
        HomeItemModel(
            id = 6,
            title = "Live Match",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.live_tv
        ),
    )
}