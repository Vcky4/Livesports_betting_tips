package com.lsbt.livesportsbettingtips.ui.screens.admin

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.tasks.Task
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.lsbt.livesportsbettingtips.data.StaticData
import com.lsbt.livesportsbettingtips.data.db.models.TipModel
import org.json.JSONException
import org.json.JSONObject
import org.koin.core.component.KoinComponent

class AdminViewModel(private val context: Application) : ViewModel(), KoinComponent {
    private val database: DatabaseReference = Firebase.database.reference
    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context)
    }
    private val fcmApi = "https://fcm.googleapis.com/fcm/send"
    private val serverKey =
        "key=" + "AAAAEeR-yAM:APA91bHoWnh8weLGtIdPywMbAwefu1DNk597kOAHDbBkWIWBZfelIIP9SD0XKCqg3rn6SW3tneosEgIEjRJ9-3j2mKyTZDOGX_9cUH_b4vMWUpEwF88t-xewg46RtxiQzktXR4h8Dfko"
    private val contentType = "application/json"
    private val _token = MutableLiveData<String>()
    private val _whatsapp = MutableLiveData("")
    private val _telegram = MutableLiveData("")
    private val _email = MutableLiveData("")
    private val _announcement = MutableLiveData<AnnouncementModel>()
    private val _tips = MutableLiveData<List<TipModel>>()
    val tips: LiveData<List<TipModel>> = _tips
    val token: LiveData<String> = _token
    val freeItems = StaticData.freeItems
    val vipItems = StaticData.vipItems
    val contactItems = StaticData.contactItems
    val announcement = _announcement
    val whatsApp = _whatsapp
    val telegram = _telegram
    val email = _email

    val getPassword = database.child("password").get()
    fun login(token: String) {
        _token.value = token
    }

    //set whatsapp number
    fun setWhatsApp(whatsApp: String) =
        database.child("contacts").child("whatsapp").setValue(whatsApp)


    //set telegram username
    fun setTelegram(telegram: String) =
        database.child("contacts").child("telegram").setValue(telegram)


    //set email address
    fun setEmail(email: String) =
        database.child("contacts").child("email").setValue(email)

    //set announcement
    fun setAnnouncement(title: String, body: String, path: String) =
        database.child(path).setValue(AnnouncementModel(body, title))
            .addOnSuccessListener {
                sendNotification(title, body)
            }

    fun clearTips(tag: String) = database.child("tips").child(tag).removeValue()

    //delete tip
    fun deleteTip(tag: String, id: String) =
        database.child("tips").child(tag).child(id).removeValue().addOnSuccessListener {
            getTips(tag)
        }

    fun saveTip(
        tag: String,
        id: String?,
        league: String,
        home: String,
        away: String,
        homeScore: String,
        awayScore: String,
        odd: String,
        status: String,
        prediction: String,
        halfScore: String = "",
        date: Long = System.currentTimeMillis()
    ): Task<Void> {
        val key = id ?: database.child("tips").child(tag).push().key
        val tip = TipModel(
            key ?: "",
            league,
            home,
            away,
            homeScore,
            awayScore,
            odd,
            date,
            status,
            halfScore,
            prediction,
        )
        return database.child("tips").child(tag).child(key ?: "").setValue(tip)
            .addOnSuccessListener {
                getTips(tag)
//                sendNotification(
//                    "New $tag\u200E\uFE0F\u200D\uD83D\uDD25",
//                    "$league\n$home vs $away\n$prediction\n$odd\u200E\uFE0F\u200D\uD83D\uDD25"
//                )
            }
    }

    //get all tips
    fun getTips(tag: String) {
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val tipListener = object : ValueEventListener {
                    private val tipList = mutableListOf<TipModel>()
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (dataValues in dataSnapshot.children) {
                            val tip = dataValues.getValue(TipModel::class.java)
                            if (tip != null) {
                                tipList.add(tip)
                            }
                        }
                        _tips.value = tipList
                        Log.d(TAG, "list value is: $tipList")
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // handle error
//                        Toast.makeText(context, "unable to update nuggets", Toast.LENGTH_SHORT).show()

                    }
                }
                database.child("tips").child(tag).ref.addListenerForSingleValueEvent(tipListener)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {
//                binding.loadingPost.visibility = GONE
//                Toast.makeText(context, "unable to update nuggets", Toast.LENGTH_SHORT).show()

            }

        }
        database.child("tips").child(tag).ref.addChildEventListener(childEventListener)
    }

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

    fun getAnnouncement(path: String) {
        //get announcement
        val announcementListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue<AnnouncementModel>()
                _announcement.value = value ?: AnnouncementModel("", "")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.child(path).addValueEventListener(announcementListener)
    }

    //get data on init
    init {
        val contactListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue<ContactModel>()
                _whatsapp.value = value?.whatsapp ?: ""
                _telegram.value = value?.telegram ?: ""
                _email.value = value?.email ?: ""
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.child("contacts").addValueEventListener(contactListener)
    }

}


//contact model
@IgnoreExtraProperties
data class ContactModel(
    var whatsapp: String?,
    var telegram: String?,
    var email: String?
) {
    constructor() : this("", "", "")

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "whatsApp" to whatsapp,
            "telegram" to telegram,
            "email" to email
        )
    }
}

@IgnoreExtraProperties
data class AnnouncementModel(
    var announcement: String?,
    var title: String?
) {
    constructor() : this("", "")

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "announcement" to announcement,
            "title" to title
        )
    }
}