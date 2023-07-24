package com.lsbt.livesportsbettingtips.data.repositories

import com.lsbt.livesportsbettingtips.data.db.daos.TipDao
import com.lsbt.livesportsbettingtips.data.db.models.HomeItemModel
import com.lsbt.livesportsbettingtips.data.db.models.TipModel

class TipsRepository(private val dao: TipDao) {
    //get all
    fun getAll() = dao.getAll()

    //create
    suspend fun create(data: TipModel) = dao.create(data)

    //create many
    suspend fun createMany(data: List<TipModel>) = dao.createMany(data)

    //delete
    suspend fun delete(data: TipModel) = dao.delete(data)

    //update
    suspend fun update(data: TipModel) = dao.update(data)
}