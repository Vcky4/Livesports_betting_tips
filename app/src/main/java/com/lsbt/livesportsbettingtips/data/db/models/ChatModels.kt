package com.lsbt.livesportsbettingtips.data.db.models

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class ChatModel(
    val key: String = "",
    val message: String = "",
    val name: String = "",
    val time: Long = 0L,
    val isAdmin: Boolean = false,
    val parent: String = ""
) {
    constructor() : this("", "", "", 0L, false, "")

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "key" to key,
            "message" to message,
            "name" to name,
            "time" to time,
            "isAdmin" to isAdmin,
            "parent" to parent
        )
    }
}

@IgnoreExtraProperties
data class ConversationModel(
    val key: String = "",
    val lastMessage: String = "",
    val name: String = "",
    val lastUpdated: Long = 0L,
) {
    constructor() : this("", "", "", 0L)

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "key" to key,
            "lastMessage" to lastMessage,
            "name" to name,
            "lastUpdated" to lastUpdated,
        )
    }

}