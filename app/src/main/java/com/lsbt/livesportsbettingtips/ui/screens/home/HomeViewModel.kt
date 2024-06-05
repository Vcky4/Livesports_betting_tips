package com.lsbt.livesportsbettingtips.ui.screens.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.lsbt.livesportsbettingtips.data.StaticData
import com.lsbt.livesportsbettingtips.data.db.models.TipModel
import com.lsbt.livesportsbettingtips.datastore.Settings
import com.lsbt.livesportsbettingtips.datastore.SettingsConstants
import com.lsbt.livesportsbettingtips.ui.screens.admin.ContactModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {
    private val settings: Settings by inject()
    private val database: DatabaseReference = Firebase.database.reference
    private val _whatsapp = MutableLiveData("")
    private val _telegram = MutableLiveData("")
    private val _email = MutableLiveData("")
    private val _isFirstTime = MutableLiveData(false)
    private val _tips = MutableLiveData<List<TipModel>>(listOf())
    val tips: LiveData<List<TipModel>> = _tips
    val whatsApp = _whatsapp
    val telegram = _telegram
    val email = _email
    val freeItems = StaticData.freeItems
    val vipItems = StaticData.vipItems
    val liveItems = StaticData.liveItems
    val contactItems = StaticData.contactItems
    val isFirstTime = _isFirstTime
    private var lastDate: Long? = null
    private val pageSize = 10


    //get all tips
    fun getTips(tag: String, loadMore: Boolean = false) {
        var query: Query =
            database.child("tips").child(tag)
                .orderByChild("date").limitToLast(pageSize)

        if (loadMore && lastDate != null) {
            Log.d("last id", "lastId is: $lastDate")
            query = database.child("tips")
                .child(tag).orderByChild("date")
                .endBefore(lastDate!!.toDouble())
                .limitToLast(pageSize)
        }

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            private val tipList = mutableListOf<TipModel>()
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataValues in dataSnapshot.children) {
                    val tip = dataValues.getValue(TipModel::class.java)
                    if (tip != null && _tips.value!!.any { it.id == tip.id }.not()) {
                        tipList.add(tip)
                    }
                }
                if (tipList.isNotEmpty()) {
                    lastDate =
                        tipList.last().date
                }
                if (loadMore) {
                    val currentList = _tips.value?.toMutableList() ?: mutableListOf()
                    currentList.addAll(0, tipList) // Prepend new tips
                    _tips.value = currentList
                } else {
                    _tips.value = tipList.reversed() // Reverse to show newest first
                }
                Log.d(ContentValues.TAG, "list value is: ${_tips.value}")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
//                Toast.makeText(context, "unable to update tips", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun loadMoreTips(tag: String) {
        getTips(tag, loadMore = true)
    }

    //set first time to false
    fun setFirstTime() {
        viewModelScope.launch {
            settings.putPreference(SettingsConstants.IS_FIRST_RUN, false)
        }
    }

    //get data on init
    init {
        viewModelScope.launch {
            settings.getPreference(SettingsConstants.IS_FIRST_RUN, false).collect {
                if (it) {
                    _isFirstTime.value = true
                }
            }
        }
        val contactListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue<ContactModel>()
                Log.d(ContentValues.TAG, "Value is: $value")
                _whatsapp.value = value?.whatsapp ?: ""
                _telegram.value = value?.telegram ?: ""
                _email.value = value?.email ?: ""
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        database.child("contacts").addValueEventListener(contactListener)
    }
}

