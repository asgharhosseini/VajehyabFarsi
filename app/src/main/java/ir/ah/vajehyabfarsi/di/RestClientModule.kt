package ir.ah.vajehyabfarsi.di

import com.squareup.moshi.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.components.*
import ir.ah.vajehyabfarsi.data.remot.*
import okhttp3.*
import retrofit2.*
import retrofit2.converter.moshi.*
import retrofit2.converter.scalars.*
import javax.inject.*

@Module
@InstallIn(SingletonComponent::class)
object RestClientModule {

    @Provides
    @Singleton
    internal fun provideApiService(
        @VajehyabfarsiRetrofit retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @VajehyabfarsiRetrofit
    @Singleton
    internal fun provideHiCityRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(EndPoints.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class VajehyabfarsiRetrofit