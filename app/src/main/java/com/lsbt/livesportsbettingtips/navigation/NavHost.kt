package com.lsbt.livesportsbettingtips.navigation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.os.LocaleListCompat
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.lsbt.livesportsbettingtips.R
import com.lsbt.livesportsbettingtips.ui.screens.NavGraphs
import com.lsbt.livesportsbettingtips.ui.screens.appCurrentDestinationAsState
import com.lsbt.livesportsbettingtips.ui.screens.destinations.AdminDestination
import com.lsbt.livesportsbettingtips.ui.screens.destinations.AnnouncementDestination
import com.lsbt.livesportsbettingtips.ui.screens.destinations.ChatDestination
import com.lsbt.livesportsbettingtips.ui.screens.destinations.Destination
import com.lsbt.livesportsbettingtips.ui.screens.destinations.NotificationsDestination
import com.lsbt.livesportsbettingtips.ui.screens.destinations.PdfDisplayDestination
import com.lsbt.livesportsbettingtips.ui.screens.home.HomeViewModel
import com.lsbt.livesportsbettingtips.ui.screens.startAppDestination
import com.lsbt.livesportsbettingtips.ui.theme.Background
import com.lsbt.livesportsbettingtips.ui.theme.Primary
import com.lsbt.livesportsbettingtips.utils.getAppVersion
import com.lsbt.livesportsbettingtips.utils.sendMail
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.navigate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

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
    val whatsapp = homeViewModel.whatsApp.observeAsState("").value
    val telegram = homeViewModel.telegram.observeAsState("").value
    val email = homeViewModel.email.observeAsState("").value
    var openDialog by remember {
        mutableStateOf(false)
    }
    val isFirstTime = homeViewModel.isFirstTime.observeAsState(false).value
    var clickCount by remember { mutableStateOf(0) }
    var lastClickTime by remember { mutableStateOf(0L) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            if (System.currentTimeMillis() - lastClickTime >= 3000) {
                clickCount = 0 // Reset click count if no new click after 3 seconds
            }
        }
    }
    LaunchedEffect(key1 = isFirstTime) {
        if (isFirstTime) {
            openDialog = true
            homeViewModel.setFirstTime()
        }
    }

    ModalNavigationDrawer(
        gesturesEnabled = false,
        drawerState = drawerState,
        drawerContent = {
            Box(
                Modifier
                    .fillMaxSize()
                    .clickable {
                        scope.launch {
                            drawerState.close()
                        }
                    }
            ) {
                Column(
                    Modifier
                        .background(Primary)
                        .fillMaxWidth(0.6f)
                ) {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        stringResource(id = R.string.about_us),
                        color = Background,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navController.navigate(PdfDisplayDestination("about"))
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                    )
                    Text(
                        stringResource(id = R.string.set_langauge),
                        color = Background,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                openDialog = true
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
                            stringResource(id = R.string.contact_us),
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
                            val appName = stringResource(id = R.string.app_name)
                            Text(
                                stringResource(id = R.string.email),
                                color = Background,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(bottom = 16.dp, start = 30.dp)
                                    .clickable {
                                        scope.launch {
                                            context.sendMail(
                                                email,
                                                appName
                                            )
                                            drawerState.close()
                                        }
                                    }
                            )
//                        Text(
//                            stringResource(id = R.string.whatsapp),
//                            color = Background,
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier
//                                .padding(start = 30.dp, bottom = 16.dp)
//                                .clickable {
//                                    scope.launch {
//                                        context.openWhatsApp(whatsapp)
//                                        drawerState.close()
//                                    }
//                                }
//                        )
                            Text(
                                stringResource(id = R.string.chat_with_us),
                                color = Background,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp, start = 30.dp, bottom = 16.dp)
                                    .clickable {
                                        navController.navigate(ChatDestination())
                                    }
                            )
                        }
                    }
                    Text(
                        stringResource(id = R.string.privacy_policy),
                        color = Background,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navController.navigate(PdfDisplayDestination("privacy"))
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                    )
                    Text(
                        stringResource(id = R.string.terms_and_conditions),
                        color = Background,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navController.navigate(PdfDisplayDestination("terms"))
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
                                lastClickTime = System.currentTimeMillis()
                                clickCount++
                                if (clickCount >= 15) {
                                    clickCount = 0 // Reset click count
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(AdminDestination)
                                }
                            }
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                }

                IconButton(
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = stringResource(id = androidx.compose.ui.R.string.close_drawer)
                    )
                }
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
                            || currentDestination == PdfDisplayDestination
                            || currentDestination == AnnouncementDestination
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
                                    || currentDestination == PdfDisplayDestination
                                    || currentDestination == AnnouncementDestination
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
                        stringResource(id = R.string.app_name),
                        color = Background,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (currentDestination != NotificationsDestination
                        && currentDestination != AnnouncementDestination
                    ) {
                        IconButton(onClick = {
                            if (currentDestination == AdminDestination) {
                                navController.navigate(NotificationsDestination)
                            } else {
                                navController.navigate(AnnouncementDestination)
                            }
                        }) {
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
            if (openDialog) {
                Dialog(
                    onDismissRequest = {
                        openDialog = false
                    }
                ) {
                    Card(
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                text = "Choose language",
                                style = MaterialTheme.typography.headlineLarge
                            )

                            OutlinedButton(
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
                                border = BorderStroke(0.dp, Color.Transparent),
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    AppCompatDelegate.setApplicationLocales(
                                        LocaleListCompat.create(
                                            Locale("en")
                                        )
                                    )
                                    openDialog = false
                                }
                            ) {
                                Text(text = "English")
                            }

                            OutlinedButton(
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
                                border = BorderStroke(0.dp, Color.Transparent),
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    AppCompatDelegate.setApplicationLocales(
                                        LocaleListCompat.create(
                                            Locale("es")
                                        )
                                    )
                                    openDialog = false
                                }
                            ) {
                                Text(text = "Spanish")
                            }

                            OutlinedButton(
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
                                border = BorderStroke(0.dp, Color.Transparent),
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    AppCompatDelegate.setApplicationLocales(
                                        LocaleListCompat.create(
                                            Locale("fr")
                                        )
                                    )
                                    openDialog = false
                                }
                            ) {
                                Text(text = "French")
                            }

                            OutlinedButton(
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
                                border = BorderStroke(0.dp, Color.Transparent),
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    AppCompatDelegate.setApplicationLocales(
                                        LocaleListCompat.create(
                                            Locale("pt")
                                        )
                                    )
                                    openDialog = false
                                }
                            ) {
                                Text(text = "Portuguese")
                            }

                            OutlinedButton(
                                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
                                border = BorderStroke(0.dp, Color.Transparent),
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    AppCompatDelegate.setApplicationLocales(
                                        LocaleListCompat.getEmptyLocaleList()
                                    )
                                    openDialog = false
                                }
                            ) {
                                Text(text = "System default")
                            }
                        }
                    }
                }
            }
        }
    }

}