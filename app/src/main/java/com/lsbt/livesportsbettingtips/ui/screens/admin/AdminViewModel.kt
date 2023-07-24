package com.lsbt.livesportsbettingtips.ui.screens.admin

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
import org.koin.core.component.KoinComponent

class AdminViewModel : ViewModel(), KoinComponent {
    private val database: DatabaseReference = Firebase.database.reference
    private val _token = MutableLiveData<String>()
    private val _whatsapp = MutableLiveData("")
    private val _telegram = MutableLiveData("")
    private val _email = MutableLiveData("")
    val token: LiveData<String> = _token
    val freeItems = StaticData.freeItems
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


    //get data on init
    init {
        val contactListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue<ContactModel>()
                Log.d(TAG, "Value is: $value")
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