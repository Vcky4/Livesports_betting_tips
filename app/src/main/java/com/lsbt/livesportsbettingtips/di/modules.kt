package com.lsbt.livesportsbettingtips.di

import androidx.room.Room
import com.lsbt.livesportsbettingtips.data.db.DB
import com.lsbt.livesportsbettingtips.ui.screens.free.FreeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    viewModel {
        FreeViewModel()
    }

    //database
    single {
        Room.databaseBuilder(
            androidContext(),
            DB::class.java,
            "LSBT"
        ).build()
    }
}