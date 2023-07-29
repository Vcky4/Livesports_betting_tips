package com.lsbt.livesportsbettingtips.di

import com.lsbt.livesportsbettingtips.datastore.Settings
import com.lsbt.livesportsbettingtips.ui.screens.admin.AdminViewModel
import com.lsbt.livesportsbettingtips.ui.screens.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    viewModel {
        HomeViewModel()
    }

    viewModel {
        AdminViewModel()
    }

    single {
        Settings(androidApplication())
    }

//    //database
//    single {
//        Room.databaseBuilder(
//            androidContext(),
//            DB::class.java,
//            "LSBT"
//        ).build()
//    }

    //Doa
//    single { get<DB>().tipDao() }

    //Repositories
//    single {
//        TipsRepository(get())
//    }
}