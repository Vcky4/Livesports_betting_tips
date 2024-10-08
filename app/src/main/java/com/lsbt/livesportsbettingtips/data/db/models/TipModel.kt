package com.lsbt.livesportsbettingtips.data.db.models

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

//@Entity("tips")
@IgnoreExtraProperties
data class TipModel(
//    @PrimaryKey(autoGenerate = true)
    val id: String,
    val league: String,
    val home: String,
    val away: String,
    val homeScore: String,
    val awayScore: String,
    val odd: String,
    val date: Long = System.currentTimeMillis(),
    val status: String,
    val prediction: String,
    val halfScore: String = "",
) {
    constructor() : this("", "", "", "", "", "", "", 0, "", "","")

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "key" to id,
            "league" to league,
            "home" to home,
            "away" to away,
            "homeScore" to homeScore,
            "awayScore" to awayScore,
            "odd" to odd,
            "date" to date,
            "status" to status,
            "prediction" to prediction,
            "halfScore" to halfScore,
        )
    }
}
