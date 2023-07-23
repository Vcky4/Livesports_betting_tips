package com.lsbt.livesportsbettingtips.ui.screens.admin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun Admin(){
    val viewModel: AdminViewModel = koinViewModel()
    val token = viewModel.token.value ?: ""

    AnimatedVisibility(visible = token.isNotEmpty()) {

    }

    if (token.isEmpty()) {
        Login(viewModel)
    }

}