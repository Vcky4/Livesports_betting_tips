package com.lsbt.livesportsbettingtips.data

import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.data.db.models.HomeItemModel
import com.lsbt.livesportsbettingtips.data.db.models.NotificationModel
import com.lsbt.livesportsbettingtips.data.db.models.TipModel

object StaticData {
    val freeItems = listOf(
        HomeItemModel(
            id = 1,
            title = R.string.dialy_sure_tips,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.tips_and_updates
        ),
        HomeItemModel(
            id = 2,
            title = R.string.football_tips,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_basketball
        ),
        HomeItemModel(
            id = 3,
            title = R.string.basketball_tips,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_basketball
        ),
        HomeItemModel(
            id = 4,
            title = R.string.tennis_tips,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_tennis
        ),
        HomeItemModel(
            id = 5,
            title = R.string.over_under,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.tips_and_updates
        ),
        HomeItemModel(
            id = 6,
            title = R.string.free_200,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_score
        ),
    )

    val vipItems = listOf(
        HomeItemModel(
            id = 1,
            title = R.string.correct_scores,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_score
        ),
        HomeItemModel(
            id = 2,
            title = R.string.fixed_draws,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.gps_fixed
        ),
        HomeItemModel(
            id = 3,
            title = R.string.odds_vip,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.casino
        ),
        HomeItemModel(
            id = 4,
            title = R.string.special_offers,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.local_offer
        ),
        HomeItemModel(
            id = 5,
            title = R.string.previous_correct_score,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.preview
        ),
        HomeItemModel(
            id = 6,
            title = R.string.previous_draws_results,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.bar_chart
        ),
    )

    val liveItems = listOf(
        HomeItemModel(
            id = 5,
            title = R.string.live_score,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.sports_score,
        ),
        HomeItemModel(
            id = 6,
            title = R.string.live_match,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.live_tv
        ),
    )

    val contactItems = listOf(
//        HomeItemModel(
//            id = 5,
//            title = R.string.whatsapp,
//            description = "Last Updated 2 Weeks Ago",
//            image = R.drawable.whatsapp,
//        ),
        HomeItemModel(
            id = 6,
            title = R.string.email,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.outline_email
        ),
//        HomeItemModel(
//            id = 7,
//            title = R.string.telegram,
//            description = "Last Updated 2 Weeks Ago",
//            image = R.drawable.telegram
//        ),
        HomeItemModel(
            id = 8,
            title = R.string.chat_with_us,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.chat,
        ),
        HomeItemModel(
            id = 9,
            title = R.string.rate_us,
            description = "Last Updated 2 Weeks Ago",
            image = R.drawable.star_rate_24,
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