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
import org.json.JSONObject
import org.koin.core.component.KoinComponent

class AdminViewModel(private val context: Application) : ViewModel(), KoinComponent {
    private val database: DatabaseReference = Firebase.database.reference
    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context)
    }
    private val fcmApi = "https://fcm.googleapis.com/fcm/send"
    private val serverKey =
        "key=" + "BGvmn4llc1gnffZ5n1zwd8XifRN38wtZqjpsjLejqxXYRWrYh-b9tjC8PWJCWlBoJK-HudZPj2nukbiUDJX82p0"
    private val contentType = "application/json"
    private val _token = MutableLiveData<String>()
    private val _whatsapp = MutableLiveData("")
    private val _telegram = MutableLiveData("")
    private val _email = MutableLiveData("")
    private val _tips = MutableLiveData<List<TipModel>>()
    val tips: LiveData<List<TipModel>> = _tips
    val token: LiveData<String> = _token
    val freeItems = StaticData.freeItems
    val vipItems = StaticData.vipItems
    val contactItems = StaticData.contactItems
    val whatsApp = _whatsapp
    val telegram = _telegram
    val email = _email
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
            prediction,
        )
        return database.child("tips").child(tag).child(key ?: "").setValue(tip)
            .addOnSuccessListener {
                getTips(tag)
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

    private fun sendNotification(notification: JSONObject) {
        Log.e("TAG", "sendNotification")
        val jsonObjectRequest = object : JsonObjectRequest(fcmApi, notification,
            Response.Listener<JSONObject> { response ->
                Log.i("TAG", "onResponse: $response")
            },
            Response.ErrorListener {
                Toast.makeText(context, "Request error", Toast.LENGTH_LONG).show()
                Log.i("TAG", "onErrorResponse: Didn't work")
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