package com.lsbt.livesportsbettingtips

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.Constants.TAG
import com.google.firebase.messaging.ktx.messaging
import com.lsbt.livesportsbettingtips.navigation.NavHost
import com.lsbt.livesportsbettingtips.ui.theme.LivesportsBettingTipsTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.messaging.subscribeToTopic("Tips")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
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