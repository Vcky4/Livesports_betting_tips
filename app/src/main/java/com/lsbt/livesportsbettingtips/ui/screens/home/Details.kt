package com.lsbt.livesportsbettingtips.ui.screens.home

import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.lsbt.livesportsbettingtips.utils.isSameDay
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import java.util.Date

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
    var page by remember { mutableStateOf(0) }
    val tips = viewModel.tips.observeAsState(listOf()).value.asSequence().filter {
        //it.date is not future
        it.date < System.currentTimeMillis() || DateUtils.isToday(it.date)
    }.toList()
//    val pagerState = rememberPagerState(
//        0
//    )
    val prev = stringResource(id = R.string.previous_correct_score)
    val prev2 = stringResource(id = R.string.previous_draws_results)
    val isHistory = trigger == prev || trigger == prev2

    var selectedTabIndex by remember {
        mutableStateOf(if (isHistory) 1 else 0)
    }
//    LaunchedEffect(selectedTabIndex) {
//        pagerState.animateScrollToPage(selectedTabIndex)
//    }
//    LaunchedEffect(pagerState.currentPage) {
//        selectedTabIndex = pagerState.currentPage
//    }
    val oneDayMillis = 24 * 60 * 60 * 1000L
    val today = System.currentTimeMillis()
    val targetDate = today - (page.plus(1) * oneDayMillis)
    val history = if (isHistory) {
        tips.sortedByDescending { it.date }
    } else {
        tips.asSequence()
            .filter { isSameDay(it.date, targetDate) }
            .sortedByDescending { it.date }
            .toList()
    }

//    LaunchedEffect(history) {
//        if (tips.isNotEmpty() && tips.indexOf(history.last()) < tips.size - 2) {
//            viewModel.loadMoreTips(trigger)
//        }
//    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
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
            if (trigger == "Free 200 plus Odds") {
                Text(
                    text = stringResource(id = R.string.coming_soon),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 30.dp),
                    textAlign = TextAlign.Center
                )
            }
//        HorizontalPager(
//            state = pagerState,
//            pageCount = 2,
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//        ) { index ->
            when (selectedTabIndex) {
                0 -> LazyColumn {
                    if (trigger != prev && trigger != prev2) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            tabItems.forEachIndexed { index, item ->
//                val isSelected = selectedTabIndex == index
//                val backgroundColor = if (isSelected) Primary else Color.Transparent
//                Box(modifier = Modifier
//                    .weight(0.5f)
//                    .height(60.dp)
//                    .clickable { selectedTabIndex = index }
//                    .background(color = backgroundColor),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = item.title,
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White,
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        textAlign = TextAlign.Center
//                    )
//                }
//
//            }
//        }
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
                                items = tips.asSequence().filter { DateUtils.isToday(it.date) }
                                    .toList()
                            ) {
                                DetailItem(it, isHistory = isHistory)
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
                            items = history
                        ) {
                            val index = tips.indexOf(it)
                            if (index == tips.size - 1) {
                                viewModel.loadMoreTips(trigger)
                            }
                            DetailItem(it, isHistory = isHistory)
                        }
                    }
                    if (history.isNotEmpty() && isHistory.not()) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically,
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
                                Text(
                                    text = if (history.isNotEmpty()) {
                                        "${
                                            Date(history[0].date).date
                                        }/${Date(history[0].date).month.plus(1)}/${
                                            Date(history[0].date).year.plus(
                                                1900
                                            )
                                        }"
                                    } else "No Tips",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp),
                                    textAlign = TextAlign.Center
                                )
                                Button2(
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    text = stringResource(id = R.string.next),
                                    color = Color.Black,
                                    contentColor = Color.White,
                                    onClick = {
                                        viewModel.loadMoreTips(trigger)
//                                    if (page < tips.size - 1) {
                                        page++
//                                    }
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                }
            }

//        }
        }
//    if (history.isNotEmpty()) {
//
//    }
        if (!isHistory) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween
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
    }
}


data class TabItem(
    val title: String
)


