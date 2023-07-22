package com.lsbt.livesportsbettingtips.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("freeTips")
data class FreeModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val date: Long = System.currentTimeMillis(),
    val image: Int
)
