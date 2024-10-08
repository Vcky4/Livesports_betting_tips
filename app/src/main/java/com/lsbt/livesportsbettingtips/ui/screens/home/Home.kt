package com.lsbt.livesportsbettingtips.ui.screens.home

import android.app.Activity
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.play.core.review.ReviewManagerFactory
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.ui.screens.destinations.ChatDestination
import com.lsbt.livesportsbettingtips.ui.screens.destinations.DetailScreenDestination
import com.lsbt.livesportsbettingtips.ui.screens.destinations.MyWebViewDestination
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
    val context = LocalContext.current
    val whatsapp = homeViewModel.whatsApp.observeAsState().value
    val telegram = homeViewModel.telegram.observeAsState().value
    val email = homeViewModel.email.observeAsState().value

    Column(Modifier.verticalScroll(rememberScrollState())) {
        Text(
            text = stringResource(id = R.string.free),
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
            modifier = Modifier
                .heightIn(max = 230.dp)
                .padding(horizontal = 8.dp)
        ) {
            items(
                items = freeItems,
            ) {
                val title = when (it.title) {
                    R.string.dialy_sure_tips -> "Daily Sure Tips"
                    R.string.football_tips -> "Football tips"
                    R.string.basketball_tips -> "Basketball tips"
                    R.string.over_under -> "Over Under"
                    R.string.free_200 -> "Free 200 plus Odds"
                    else -> "Tennis tips"
                }
                HomeItem(it) {
                    navigator.navigate(
                        DetailScreenDestination(
                            title
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.vip),
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
            modifier = Modifier
                .heightIn(max = 230.dp)
                .padding(horizontal = 8.dp)
        ) {
            items(
                items = vipItems,
            ) {
                val title = when (it.title) {
                    R.string.previous_draws_results -> "Previous Draws Results"
                    else -> "Previous Correct Score"
                }
                HomeItem(it) {
                    if (it.title == R.string.previous_correct_score
                        || it.title == R.string.previous_draws_results
                    ) {
                        navigator.navigate(DetailScreenDestination(title))
                    } else {
                        navigator.navigate(VipPinDestination(title))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.live),
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
            modifier = Modifier
                .heightIn(max = 230.dp)
                .padding(horizontal = 8.dp)
        ) {
            items(
                items = liveItems,
            ) {
                HomeItem(it) {
                    if (it.id == 5) {
                        navigator.navigate(MyWebViewDestination("https://www.livescore.com"))
                    } else {
                        navigator.navigate(MyWebViewDestination("https://www.livesports088.com"))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.contact_us),
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
            modifier = Modifier
                .heightIn(max = 230.dp)
                .padding(horizontal = 8.dp)
        ) {
            items(
                items = contactItems,
            ) {
                HomeItem(it) {
                    when (it.id) {
                        5 -> context.openWhatsApp(whatsapp ?: "")
                        6 -> context.sendMail(
                            email ?: "",
                            "Live Sports Betting Tips"
                        )

                        8 -> navigator.navigate(ChatDestination())
                        9 -> {
                            val manager = ReviewManagerFactory.create(context)
                            val request = manager.requestReviewFlow()
                            request.addOnCompleteListener { request ->
                                if (request.isSuccessful) {
                                    // We got the ReviewInfo object
                                    val reviewInfo = request.result
                                    val flow = manager.launchReviewFlow(
                                        context as Activity,
                                        reviewInfo
                                    )
                                    flow.addOnCompleteListener { _ ->
                                        // The flow has finished. The API does not indicate whether the user
                                        // reviewed or not, or even whether the review dialog was shown. Thus, no
                                        // matter the result, we continue our app flow.
                                    }
                                } else {
                                    // There was some problem, continue regardless of the result.
                                }
                            }
                        }

                        else -> context.openTelegram(telegram ?: "")
                    }
                }
            }
        }
    }
}
