package com.lsbt.livesportsbettingtips.utils

import java.util.Calendar

//object LocaleHelper {
//    fun setLocale(context: Context, language: String): Context? {
//        MyApp.myLang = language
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            return updateResources(context, language);
//        }
//        return updateResourcesLegacy(context, language);
//    }
//
//    @TargetApi(Build.VERSION_CODES.N)
//    private fun updateResources(context: Context, language: String): Context? {
//        val locale = Locale(language)
//        Locale.setDefault(locale)
//        val configuration = context.resources.configuration
//        configuration.setLocale(locale)
//        configuration.setLayoutDirection(locale)
//        return context.createConfigurationContext(configuration)
//    }
//
//    private fun updateResourcesLegacy(context: Context, language: String): Context {
//        val locale = Locale(language)
//        Locale.setDefault(locale)
//        val resources = context.resources
//        val configuration = resources.configuration
//        configuration.locale = locale
//        resources.updateConfiguration(configuration, resources.displayMetrics)
//        return context
//    }
//}

// Helper function to check if two timestamps are on the same day
fun isSameDay(time1: Long, time2: Long): Boolean {
    val calendar1 = Calendar.getInstance().apply { timeInMillis = time1 }
    val calendar2 = Calendar.getInstance().apply { timeInMillis = time2 }
    return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
            calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
}