package com.lsbt.livesportsbettingtips.ui.screens.free

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.data.StaticData
import com.lsbt.livesportsbettingtips.data.db.daos.FreeDao
import com.lsbt.livesportsbettingtips.data.db.models.FreeModel
import com.lsbt.livesportsbettingtips.data.repositories.FreeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FreeViewModel : ViewModel(), KoinComponent {
    private val freeRepository: FreeRepository by inject()
    val freeItems = freeRepository.getAll()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            freeRepository.createMany(StaticData.freeItems)
        }
    }
}

