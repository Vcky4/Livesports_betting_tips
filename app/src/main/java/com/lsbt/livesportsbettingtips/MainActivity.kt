package com.lsbt.livesportsbettingtips

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.lsbt.livesportsbettingtips.datastore.Settings
import com.lsbt.livesportsbettingtips.datastore.SettingsConstants
import com.lsbt.livesportsbettingtips.navigation.NavHost
import com.lsbt.livesportsbettingtips.ui.theme.LivesportsBettingTipsTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LivesportsBettingTipsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost()
                }
            }
        }
    }
//    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(
//            LocaleHelper.setLocale(newBase, language)
//        )
//    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    LivesportsBettingTipsTheme {
//        Greeting("Android")
//    }
//}