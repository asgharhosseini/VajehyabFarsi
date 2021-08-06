package ir.ah.vajehyabfarsi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
        headerInterceptor: CommonHeadersInterceptor,
        authorizationHeaderInterceptor: AuthorizationHeaderInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(headerInterceptor)
        .addInterceptor(authorizationHeaderInterceptor)
        .build()
}