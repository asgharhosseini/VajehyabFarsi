package ir.ah.vajehyabfarsi.di

import android.content.SharedPreferences
import ir.ah.vajehyabfarsi.*
import javax.inject.Inject
import javax.inject.Named

class UserSharedPreferencesHelper @Inject constructor(
    @Named(userSharedPreferences)
    private val sp: SharedPreferences
) {

    var token: String
        get() = sp.getString("SP_ACCESS_TOKEN", null).orEmpty()
        set(value) = sp.edit().putString("SP_ACCESS_TOKEN", value).apply()

    var fcm_token: String
        get() = sp.getString("SP_FCM_TOKEN", null).orEmpty()
        set(value) = sp.edit().putString("SP_FCM_TOKEN", value).apply()

    var refreshToken: String
        get() = sp.getString("SP_REFRESH_TOKEN", null).orEmpty()
        set(value) = sp.edit().putString("SP_REFRESH_TOKEN", value).apply()

    var expireDate: Int
        get() = sp.getInt("SP_EXPIRE_DATE_TOKEN", -1)
        set(value) = sp.edit().putInt("SP_EXPIRE_DATE_TOKEN", value).apply()

    var locale: String
        get() = sp.getString("SP_LANG", "Tr").orEmpty()
        set(value) = sp.edit().putString("SP_LANG", value).apply()


    var skipVersion: Int
        get() = sp.getInt("SP_SKIP_VERSION", BuildConfig.VERSION_CODE)
        set(value) = sp.edit().putInt("SP_SKIP_VERSION", value).apply()

    fun clear() = sp.edit().clear().apply()
    fun revokeAccessToken() = sp.edit().remove("SP_ACCESS_TOKEN").apply()
    fun revokeRefreshToken() = sp.edit().remove("SP_REFRESH_TOKEN").apply()
}