package com.lsbt.livesportsbettingtips.data

import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.data.db.models.HomeItemModel
import com.lsbt.livesportsbettingtips.data.db.models.NotificationModel
import com.lsbt.livesportsbettingtips.data.db.models.TipModel

object StaticData {
    val freeItems = listOf(
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
            image = R.drawable.sports_basketball
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
    )

    val vipItems = listOf(
        HomeItemModel(
            id = 1,
            title = "Correct Score",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_score
        ),
        HomeItemModel(
            id = 2,
            title = "Fixed draws",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.gps_fixed
        ),
        HomeItemModel(
            id = 3,
            title = "50+ odds vip",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.casino
        ),
        HomeItemModel(
            id = 4,
            title = "Special offers",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.local_offer
        ),
        HomeItemModel(
            id = 5,
            title = "Previous correct score",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.preview
        ),
        HomeItemModel(
            id = 6,
            title = "Previous Draws results",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.bar_chart
        ),
    )

    val liveItems = listOf(
        HomeItemModel(
            id = 5,
            title = "Live Score",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_score,
        ),
        HomeItemModel(
            id = 6,
            title = "Live Match",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.live_tv
        ),
    )

    val contactItems = listOf(
        HomeItemModel(
            id = 5,
            title = "WhatsApp",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.whatsapp,
        ),
        HomeItemModel(
            id = 6,
            title = "Email",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.outline_email
        ),
        HomeItemModel(
            id = 7,
            title = "Telegram",
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.telegram
        ),
    )

    val tips = listOf<TipModel>(

    )

    val notification = listOf(
        NotificationModel(
            id = 1,
            title = "New Tips",
            body = "New tips are available",
            date = System.currentTimeMillis()
        ),
        NotificationModel(
            id = 2,
            title = "New Tips",
            body = "New tips are available",
            date = System.currentTimeMillis()
        ),
    )
}