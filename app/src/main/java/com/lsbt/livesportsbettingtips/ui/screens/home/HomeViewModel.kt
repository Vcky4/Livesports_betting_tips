package com.lsbt.livesportsbettingtips.ui.screens.home

import androidx.lifecycle.ViewModel
import com.lsbt.livesportsbettingtips.data.StaticData
import org.koin.core.component.KoinComponent

class HomeViewModel : ViewModel(), KoinComponent {
    //    private val freeRepository: FreeRepository by inject()
    val freeItems = StaticData.freeItems
    val vipItems = StaticData.vipItems
    val liveItems = StaticData.liveItems
    val contactItems = StaticData.contactItems
    val whatsApp ="+254718290781"
    val telegram = "https://t.me/LSBTips"
    val email = "Livesportstips1@gmail.com"

    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            freeRepository.createMany(StaticData.freeItems)
//        }
    }
}

