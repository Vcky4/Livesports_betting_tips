package com.lsbt.livesportsbettingtips.data.repositories

import com.lsbt.livesportsbettingtips.data.db.daos.FreeDao
import com.lsbt.livesportsbettingtips.data.db.models.HomeItemModel

class FreeRepository(private val dao: FreeDao) {
    //get all
    fun getAll() = dao.getAll()

    //create
    suspend fun create(data: HomeItemModel) = dao.create(data)

    //create many
    suspend fun createMany(data: List<HomeItemModel>) = dao.createMany(data)

    //delete
    suspend fun delete(data: HomeItemModel) = dao.delete(data)

    //update
    suspend fun update(data: HomeItemModel) = dao.update(data)
}