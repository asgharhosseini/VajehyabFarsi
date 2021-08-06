package ir.ah.vajehyabfarsi.di
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationHeaderInterceptor @Inject constructor(
    private val userSP: UserSharedPreferencesHelper
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request().newBuilder()
        if (userSP.token.isNotEmpty()) {
            originalRequest.addHeader(
                "Authorization",
                "Bearer ${userSP.token}"
            )
        }
        val newRequest = originalRequest.build()
        return chain.proceed(newRequest)
    }
}