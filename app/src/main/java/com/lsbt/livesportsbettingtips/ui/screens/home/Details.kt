package com.lsbt.livesportsbettingtips.ui.screens.home

import android.text.format.DateUtils
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.components.Button2
import com.lsbt.livesportsbettingtips.ui.theme.Primary
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalStdlibApi::class)
@Destination
@Composable
fun DetailScreen(trigger: String, navigator: DestinationsNavigator) {
    val viewModel: HomeViewModel = koinViewModel()
    LaunchedEffect(key1 = viewModel) {
        viewModel.getTips(trigger)
    }
    val tabItems = listOf(
        TabItem(
            title = "Today"
        ),
        TabItem(
            title = "History"
        ),
    )
    var page by remember{ mutableStateOf(0) }
    val tips = viewModel.tips.observeAsState(listOf()).value
    val pagerState = rememberPagerState(
        0
    )
    val prev = stringResource(id = R.string.previous_correct_score)
    val prev2 = stringResource(id = R.string.previous_draws_results)

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }
    val history = when (trigger) {
        prev -> tips.sortedByDescending { it.date }
        prev2 -> tips.sortedByDescending { it.date }
        else -> tips.filter { !DateUtils.isToday(it.date) }.sortedByDescending { it.date }
    }

    Column (modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navigator.navigateUp() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = stringResource(id = R.string.back),
                    tint = Color.White
                )
            }
            Text(
                text = when (trigger) {
                    prev -> stringResource(id = R.string.correct_scores)
                    prev2 -> stringResource(id = R.string.fixed_draws)
                    else -> trigger
                },
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        HorizontalPager(
            state = pagerState,
            pageCount = 2,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            when (index) {
                0 -> LazyColumn {
                    if (trigger != prev && trigger != prev2) {

                        if (tips.none { DateUtils.isToday(it.date) }) {
                            item {
                                Text(
                                    text = stringResource(id = R.string.no_tips_available),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 30.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            items(
                                items = tips.filter { DateUtils.isToday(it.date) }
                            ) {
                                DetailItem(it)
                            }
                        }
                    }
                }

                else -> LazyColumn {
                    if (history.isEmpty()) {
                        item {
                            Text(
                                text = stringResource(id = R.string.no_tips_available),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 30.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        items(
                            items = history.slice(page..page.plus(2))
                        ) {
                            DetailItem(it)
                        }
                    }
                    if (history.isNotEmpty()) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxSize()
                                    .padding(end = 40.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Button2(
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    text = stringResource(id = R.string.back),
                                    color = Color.Black,
                                    contentColor = Color.White,
                                    onClick = {
                                        if (page > 0) {
                                            page--
                                        }
                                    }
                                )
                                Button2(
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    text = stringResource(id = R.string.next),
                                    color = Color.Black,
                                    contentColor = Color.White,
                                    onClick = {
                                        if (page < history.size - 1) {
                                            page++
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }

        }
    }
//    if (history.isNotEmpty()) {
//
//    }
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.Bottom
    ) {
        tabItems.forEachIndexed { index, item ->
            val isSelected = selectedTabIndex == index
            val backgroundColor = if (isSelected) Primary else Color.Transparent
            Box(modifier = Modifier
                .weight(0.5f)
                .height(60.dp)
                .clickable { selectedTabIndex = index }
                .background(color = backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}


data class TabItem(
    val title: String
)


