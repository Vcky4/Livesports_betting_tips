package com.lsbt.livesportsbettingtips.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lsbt.livesportsbettingtips.data.db.models.FreeModel

@Database(
    entities = [
        FreeModel::class
    ],
    version = 1,
    exportSchema = true
)
abstract class DB : RoomDatabase() {
}