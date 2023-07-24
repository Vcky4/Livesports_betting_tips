package com.lsbt.livesportsbettingtips.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lsbt.livesportsbettingtips.data.db.models.HomeItemModel
import com.lsbt.livesportsbettingtips.data.db.models.TipModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TipDao {
    //get all free items
    @Query("SELECT * FROM tips")
    fun getAll():Flow<List<TipModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(data: TipModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createMany(data: List<TipModel>)
    @Delete
    suspend fun delete(data: TipModel)

    @Update
    suspend fun update(data: TipModel)
}