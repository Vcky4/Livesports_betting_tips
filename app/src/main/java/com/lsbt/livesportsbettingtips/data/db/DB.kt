package com.lsbt.livesportsbettingtips.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lsbt.livesportsbettingtips.data.db.daos.TipDao
import com.lsbt.livesportsbettingtips.data.db.models.TipModel

@Database(
    entities = [
        TipModel::class
    ],
    version = 1,
    exportSchema = true
)
abstract class DB : RoomDatabase() {

    //free doa
    abstract fun tipDao(): TipDao
}