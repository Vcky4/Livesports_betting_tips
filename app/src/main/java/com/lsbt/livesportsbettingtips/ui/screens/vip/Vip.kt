package com.lsbt.livesportsbettingtips.ui.screens.vip

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun Vip(){
    LazyColumn {
        items(10) {
            VipItem()
        }
    }
}