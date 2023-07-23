package com.lsbt.livesportsbettingtips.ui.screens.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class AdminViewModel: ViewModel(), KoinComponent {
    private val _token = MutableLiveData("")
    val token: LiveData<String> = _token

    fun login(token: String) {
        _token.value = token
    }
}