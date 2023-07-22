package com.lsbt.livesportsbettingtips.di

import com.lsbt.livesportsbettingtips.ui.screens.free.FreeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    viewModel {
        FreeViewModel()
    }
}