package com.lsbt.livesportsbettingtips.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tips")
data class TipModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val league: String,
    val home: String,
    val away: String,
    val homeScore: String,
    val awayScore: String,
    val odd: String,
    val time: String,
    val date: Long = System.currentTimeMillis(),
    val status: String,
    val prediction: String,
)
