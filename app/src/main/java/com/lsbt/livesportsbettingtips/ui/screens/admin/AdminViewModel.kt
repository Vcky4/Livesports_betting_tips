package com.lsbt.livesportsbettingtips.ui.screens.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lsbt.livesportsbettingtips.data.StaticData
import org.koin.core.component.KoinComponent

class AdminViewModel: ViewModel(), KoinComponent {
    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token
    val freeItems = StaticData.freeItems
    val contactItems = StaticData.contactItems
    val whatsApp ="+254718290781"
    val telegram = "https://t.me/LSBTips"
    val email = "Livesportstips1@gmail.com"
    fun login(token: String) {
        _token.value = token
    }
}