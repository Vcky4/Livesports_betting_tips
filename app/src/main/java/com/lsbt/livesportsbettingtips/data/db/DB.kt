package com.lsbt.livesportsbettingtips.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lsbt.livesportsbettingtips.data.db.daos.FreeDao
import com.lsbt.livesportsbettingtips.data.db.models.HomeItemModel

@Database(
    entities = [
        HomeItemModel::class
    ],
    version = 1,
    exportSchema = true
)
abstract class DB : RoomDatabase() {

    //free doa
    abstract fun freeDao(): FreeDao
}