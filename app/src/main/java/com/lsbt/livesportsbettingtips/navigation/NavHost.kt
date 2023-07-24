package com.lsbt.livesportsbettingtips.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.ui.screens.NavGraphs
import com.lsbt.livesportsbettingtips.ui.screens.appCurrentDestinationAsState
import com.lsbt.livesportsbettingtips.ui.screens.destinations.AdminDestination
import com.lsbt.livesportsbettingtips.ui.screens.destinations.Destination
import com.lsbt.livesportsbettingtips.ui.screens.destinations.NotificationsDestination
import com.lsbt.livesportsbettingtips.ui.screens.home.HomeViewModel
import com.lsbt.livesportsbettingtips.ui.screens.startAppDestination
import com.lsbt.livesportsbettingtips.ui.theme.Background
import com.lsbt.livesportsbettingtips.ui.theme.Primary
import com.lsbt.livesportsbettingtips.utils.getAppVersion
import com.lsbt.livesportsbettingtips.utils.openTelegram
import com.lsbt.livesportsbettingtips.utils.openWhatsApp
import com.lsbt.livesportsbettingtips.utils.sendMail
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.navigate
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialNavigationApi::class
)
@Composable
fun NavHost() {
    val navController = rememberAnimatedNavController()
    val homeViewModel: HomeViewModel = koinViewModel()
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    var isContactsOpen by remember {
        mutableStateOf(false)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                Modifier
                    .background(Primary)
                    .fillMaxWidth(0.6f)
            ) {
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    "About Us",
                    color = Background,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
//                            uriHandler.openUri(uri)
                            scope.launch {
                                drawerState.close()
                            }
                        }
                )
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable {
                            isContactsOpen = !isContactsOpen
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Contact Us",
                        color = Background,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = "expand",
                        tint = Background,
                        modifier = Modifier
                            .rotate(if (isContactsOpen) 90f else 0f)
                    )
                }
                AnimatedVisibility(visible = isContactsOpen) {
                    Column {
                        Text(
                            "Email",
                            color = Background,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(bottom = 16.dp, start = 30.dp)
                                .clickable {
                                    scope.launch {
                                        context.sendMail(
                                            homeViewModel.email,
                                            "Live Sports Betting Tips"
                                        )
                                        drawerState.close()
                                    }
                                }
                        )
                        Text(
                            "WhatsApp",
                            color = Background,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 30.dp, bottom = 16.dp)
                                .clickable {
                                    scope.launch {
                                        context.openWhatsApp(homeViewModel.whatsApp)
                                        drawerState.close()
                                    }
                                }
                        )
                        Text(
                            "Telegram",
                            color = Background,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 30.dp, bottom = 16.dp)
                                .clickable {
                                    scope.launch {
                                        context.openTelegram(homeViewModel.telegram)
                                        drawerState.close()
                                    }
                                }
                        )
                    }
                }
                Text(
                    "Privacy Policy",
                    color = Background,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
//                           navController.navigate(VipDestination)
                            scope.launch {
                                drawerState.close()
                            }
                        }
                )
                Text(
                    "Terms and Conditions",
                    color = Background,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
//                           navController.navigate(VipDestination)
                            scope.launch {
                                drawerState.close()
                            }
                        }
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    "V " + context.getAppVersion()?.versionName,
                    color = Background,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(AdminDestination)
                            scope.launch {
                                drawerState.close()
                            }
                        }
                )
                Spacer(modifier = Modifier.padding(20.dp))
            }


        }
    ) {
        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Primary),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        if (currentDestination == AdminDestination
                            || currentDestination == NotificationsDestination
                        ) {
                            navController.navigateUp()
                        } else {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    }) {
                        Icon(
                            painter = painterResource(
                                id = if (currentDestination == AdminDestination
                                    || currentDestination == NotificationsDestination
                                ) {
                                    R.drawable.arrow_back
                                } else {
                                    R.drawable.menu
                                }
                            ),
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
                    if (currentDestination != AdminDestination
                        && currentDestination != NotificationsDestination
                    ) {
                        IconButton(onClick = { navController.navigate(NotificationsDestination) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.notifications),
                                contentDescription = "notification",
                                tint = Background
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.padding(16.dp))
                    }
                }
            },
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = "background",
                modifier = Modifier
                    .blur(11.dp)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            DestinationsNavHost(
                navGraph = NavGraphs.root,
                navController = navController,
                engine = rememberAnimatedNavHostEngine(),
                modifier = Modifier.padding(it),
            )
        }
    }

}