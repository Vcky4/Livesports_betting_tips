package com.lsbt.livesportsbettingtips.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsbt.livesportsbettingtips.ui.screens.destinations.DetailScreenDestination
import com.lsbt.livesportsbettingtips.ui.screens.destinations.VipPinDestination
import com.lsbt.livesportsbettingtips.utils.openTelegram
import com.lsbt.livesportsbettingtips.utils.openWhatsApp
import com.lsbt.livesportsbettingtips.utils.sendMail
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun Home(navigator: DestinationsNavigator) {
    val homeViewModel: HomeViewModel = koinViewModel()
    val freeItems = homeViewModel.freeItems
    val vipItems = homeViewModel.vipItems
    val liveItems = homeViewModel.liveItems
    val contactItems = homeViewModel.contactItems
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    Column(Modifier.verticalScroll(rememberScrollState())) {
        Text(
            text = "Free",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.heightIn(max = 200.dp)
        ) {
            items(
                items = freeItems,
            ) {
                HomeItem(it) {
                    navigator.navigate(DetailScreenDestination(it.title))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "VIP",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.heightIn(max = 300.dp)
        ) {
            items(
                items = vipItems,
            ) {
                HomeItem(it) {
                    navigator.navigate(VipPinDestination(it.title))
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Live",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.heightIn(max = 200.dp)
        ) {
            items(
                items = liveItems,
            ) {
                HomeItem(it) {
                    if (it.id == 5) {
                        uriHandler.openUri("https://www.livescore.com")
                    } else {
                        uriHandler.openUri("https://www.livesports088.com")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Contact Us",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.heightIn(max = 200.dp)
        ) {
            items(
                items = contactItems,
            ) {
                HomeItem(it) {
                    when (it.id) {
                        5 -> context.openWhatsApp(homeViewModel.whatsApp)
                        6 -> context.sendMail(
                            homeViewModel.email,
                            "Live Sports Betting Tips"
                        )

                        else -> context.openTelegram(homeViewModel.telegram)
                    }
                }
            }
        }
    }
