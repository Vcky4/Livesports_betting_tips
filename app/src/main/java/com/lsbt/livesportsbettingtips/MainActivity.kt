package com.lsbt.livesportsbettingtips

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.lsbt.livesportsbettingtips.navigation.NavHost
import com.lsbt.livesportsbettingtips.ui.theme.LivesportsBettingTipsTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("es"))
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