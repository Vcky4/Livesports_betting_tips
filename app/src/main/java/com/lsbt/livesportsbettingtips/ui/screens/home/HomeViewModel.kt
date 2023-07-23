package com.lsbt.livesportsbettingtips.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsbt.livesportsbettingtips.data.StaticData
import com.lsbt.livesportsbettingtips.data.repositories.FreeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {
//    private val freeRepository: FreeRepository by inject()
    val freeItems = StaticData.freeItems

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            freeRepository.createMany(StaticData.freeItems)
//        }
    }
}

