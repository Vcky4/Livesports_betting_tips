package com.example.livesportsbettingtips.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NavHost() {
    val navController = rememberAnimatedNavController()
    Scaffold {
        //    DestinationsNavHost(
//        navGraph = NavGraphs.root,
//        navController = navController,
//        modifier = Modifier.weight(1f),
//        engine = rememberAnimatedNavHostEngine()
//    )
    }

}