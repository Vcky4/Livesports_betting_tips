package com.lsbt.livesportsbettingtips.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lsbt.livesportsbettingtips.data.db.models.HomeItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FreeDao {
    //get all free items
    @Query("SELECT * FROM freeTips")
    fun getAll():Flow<List<HomeItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(data: HomeItemModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createMany(data: List<HomeItemModel>)
    @Delete
    suspend fun delete(data: HomeItemModel)

    @Update
    suspend fun update(data: HomeItemModel)
}