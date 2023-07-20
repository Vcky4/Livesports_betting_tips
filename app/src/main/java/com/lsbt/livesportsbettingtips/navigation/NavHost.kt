package com.lsbt.livesportsbettingtips.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lsbt.livesportsbettingtips.ui.screens.NavGraphs
import com.lsbt.livesportsbettingtips.ui.theme.Primary
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.ui.theme.Background
import com.lsbt.livesportsbettingtips.ui.theme.Secondary
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialNavigationApi::class
)
@Composable
fun NavHost() {
    val navController = rememberAnimatedNavController()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Primary),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "menu",
                        tint = Background
                    )
                }
                Text(
                    "Live Sports Betting Tips",
                    color = Background,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.notifications),
                        contentDescription = "notification",
                        tint = Background
                    )
                }
            }
        },
        floatingActionButton = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Secondary, RoundedCornerShape(50))
                    .clip(RoundedCornerShape(50))
                    .padding(vertical = 14.dp, horizontal = 20.dp)
            ) {
                Text(
                    "VIP",
                    color = Background,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow_forward),
                    contentDescription = "add",
                    tint = Background
                )
            }
        },
    ) {
        Image(painter = painterResource(id = R.drawable.bacf),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
            )
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            navController = navController,
            engine = rememberAnimatedNavHostEngine(),
            modifier = Modifier.padding(it),
        )
    }

}