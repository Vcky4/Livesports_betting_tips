package com.lsbt.livesportsbettingtips.ui.screens.admin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.ui.screens.home.HomeItem
import com.lsbt.livesportsbettingtips.utils.openTelegram
import com.lsbt.livesportsbettingtips.utils.openWhatsApp
import com.lsbt.livesportsbettingtips.utils.sendMail
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun Admin(navigator: DestinationsNavigator) {
    val viewModel: AdminViewModel = koinViewModel()
    val token = viewModel.token.observeAsState(initial = "").value
    val freeItems = viewModel.freeItems
    val contactItems = viewModel.contactItems
    val context = LocalContext.current


    AnimatedVisibility(visible = token.isNotEmpty()) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
        ) {
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
                        navigator.navigate(
                            com.lsbt.livesportsbettingtips.ui.screens.destinations.DetailScreenDestination(
                                it.title
                            )
                        )
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
                            5 -> context.openWhatsApp(viewModel.whatsApp)
                            6 -> context.sendMail(
                                viewModel.email,
                                "Live Sports Betting Tips"
                            )

                            else -> context.openTelegram(viewModel.telegram)
                        }
                    }
                }
            }
        }
    }

    if (token.isEmpty()) {
        Login(viewModel)
    }
}