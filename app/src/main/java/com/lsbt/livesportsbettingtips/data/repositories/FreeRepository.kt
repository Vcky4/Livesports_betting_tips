package com.lsbt.livesportsbettingtips.data.repositories

import com.lsbt.livesportsbettingtips.data.db.daos.FreeDao
import com.lsbt.livesportsbettingtips.data.db.models.FreeModel

class FreeRepository(private val dao: FreeDao) {
    //get all
    fun getAll() = dao.getAll()

    //create
    suspend fun create(data: FreeModel) = dao.create(data)

    //create many
    suspend fun createMany(data: List<FreeModel>) = dao.createMany(data)

    //delete
    suspend fun delete(data: FreeModel) = dao.delete(data)

    //update
    suspend fun update(data: FreeModel) = dao.update(data)
}