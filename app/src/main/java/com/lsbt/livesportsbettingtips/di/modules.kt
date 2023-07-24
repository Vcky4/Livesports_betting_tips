package com.lsbt.livesportsbettingtips.di

import androidx.room.Room
import com.lsbt.livesportsbettingtips.data.db.DB
import com.lsbt.livesportsbettingtips.data.repositories.TipsRepository
import com.lsbt.livesportsbettingtips.ui.screens.admin.AdminViewModel
import com.lsbt.livesportsbettingtips.ui.screens.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    viewModel {
        HomeViewModel()
    }

    viewModel {
        AdminViewModel()
    }

    //database
    single {
        Room.databaseBuilder(
            androidContext(),
            DB::class.java,
            "LSBT"
        ).build()
    }

    //Doa
    single { get<DB>().tipDao() }

    //Repositories
    single {
        TipsRepository(get())
    }
}