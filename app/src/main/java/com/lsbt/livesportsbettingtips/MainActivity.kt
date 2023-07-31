package com.lsbt.livesportsbettingtips

import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.lsbt.livesportsbettingtips.navigation.NavHost
import com.lsbt.livesportsbettingtips.ui.theme.LivesportsBettingTipsTheme
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.ui.PdfActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = Uri.parse("android.resource://${this.packageName}" + R.raw.etietop)
        val config = PdfActivityConfiguration.Builder(this).build()
        PdfActivity.showDocument(this, uri, config)
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

}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    LivesportsBettingTipsTheme {
//        Greeting("Android")
//    }
//}