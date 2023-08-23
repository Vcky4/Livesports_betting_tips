package com.lsbt.livesportsbettingtips.ui.screens.chat

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.lsbt.livesportsbettingtips.datastore.Settings
import com.lsbt.livesportsbettingtips.datastore.SettingsConstants
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ChatViewModel(private val context: Application) : ViewModel(), KoinComponent {
    private val database: DatabaseReference = Firebase.database.reference
    val settings: Settings by inject()
    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context)
    }
    private val fcmApi = "https://fcm.googleapis.com/fcm/send"
    private val serverKey =
        "key=" + "AAAAEeR-yAM:APA91bHoWnh8weLGtIdPywMbAwefu1DNk597kOAHDbBkWIWBZfelIIP9SD0XKCqg3rn6SW3tneosEgIEjRJ9-3j2mKyTZDOGX_9cUH_b4vMWUpEwF88t-xewg46RtxiQzktXR4h8Dfko"
    private val contentType = "application/json"
    private val _chatId = MutableLiveData("")
    private val _userName = MutableLiveData("")

    val userName = _userName
    val chatId = _chatId


    fun sendNotification(title: String, body: String) {
        Log.e("TAG", "sendNotification")
        val topic = "/topics/Tips" //topic has to match what the receiver subscribed to

        val notification = JSONObject()
        val notifcationBody = JSONObject()

        try {
            notifcationBody.put("title", title)
            notifcationBody.put("message", body)   //Enter your notification message
            notification.put("to", topic)
            notification.put("data", notifcationBody)
        } catch (e: JSONException) {
            Log.e("TAG", "notification: " + e.message)
        }
        val jsonObjectRequest = object : JsonObjectRequest(fcmApi, notification,
            Response.Listener { response ->
                Log.i("TAG", "onResponse: $response")
            },
            Response.ErrorListener {
                Toast.makeText(context, "Request error", Toast.LENGTH_LONG).show()
                Log.i("TAG", "onErrorResponse: Didn't work $it")
            }) {

            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["Authorization"] = serverKey
                params["Content-Type"] = contentType
                return params
            }
        }
        requestQueue.add(jsonObjectRequest)
    }

    fun setUserName(name: String) {
        viewModelScope.launch {
            settings.putPreference(SettingsConstants.USER_NAME, name)
            _userName.value = name
        }
    }


    init {
        viewModelScope.launch {
            settings.getPreference(SettingsConstants.USER_NAME, "").collect {
                if (it.isNotEmpty()) {
                    _userName.value = it
                }
            }
        }
        viewModelScope.launch {
            settings.getPreference(SettingsConstants.CHAT_ID, "").collect {
                if (it.isNotEmpty()) {
                    _chatId.value = it
                }
            }
        }
    }

}