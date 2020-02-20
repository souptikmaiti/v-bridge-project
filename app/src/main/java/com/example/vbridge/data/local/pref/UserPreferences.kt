package com.example.vbridge.data.local.pref

import android.content.SharedPreferences
import javax.inject.Inject

class UserPreferences @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val USER_ID = "PREF_USER_ID"
        const val USER_NAME = "PREF_USER_NAME"
        const val USER_EMAIL = "PREF_USER_EMAIL"
        const val ACCESS_TOKEN = "PREF_ACCESS_TOKEN"
    }

    fun getUserId(): String? =
        prefs.getString(USER_ID, null)

    fun setUserId(userId: String) =
        prefs.edit().putString(USER_ID, userId).apply()

    fun removeUserId() =
        prefs.edit().remove(USER_ID).apply()

    fun getUserName(): String? =
        prefs.getString(USER_NAME, null)

    fun setUserName(userName: String) =
        prefs.edit().putString(USER_NAME, userName).apply()

    fun removeUserName() =
        prefs.edit().remove(USER_NAME).apply()

    fun getUserEmail(): String? =
        prefs.getString(USER_EMAIL, null)

    fun setUserEmail(email: String) =
        prefs.edit().putString(USER_EMAIL, email).apply()

    fun removeUserEmail() =
        prefs.edit().remove(USER_EMAIL).apply()

    fun getAccessToken(): String? =
        prefs.getString(ACCESS_TOKEN, null)

    fun setAccessToken(token: String) =
        prefs.edit().putString(ACCESS_TOKEN, token).apply()

    fun removeAccessToken() =
        prefs.edit().remove(ACCESS_TOKEN).apply()
}