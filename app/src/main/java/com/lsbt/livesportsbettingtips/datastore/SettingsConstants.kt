package com.lsbt.livesportsbettingtips.datastore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object SettingsConstants {
    val IS_FIRST_RUN = booleanPreferencesKey("IS_FIRST_RUN")
    val LANGUAGE = stringPreferencesKey("LANGUAGE")
    val PIN = stringPreferencesKey("PIN")
    val FCM_TOKEN = stringPreferencesKey("FCM_TOKEN")
}
