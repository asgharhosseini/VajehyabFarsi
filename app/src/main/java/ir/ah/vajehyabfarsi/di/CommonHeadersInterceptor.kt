package ir.ah.vajehyabfarsi.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CommonHeadersInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().run {
            this.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build()
        }
        return chain.proceed(request)
    }
}
