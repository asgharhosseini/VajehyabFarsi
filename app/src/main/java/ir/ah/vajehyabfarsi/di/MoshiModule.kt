package ir.ah.vajehyabfarsi.di

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.math.BigDecimal
import java.util.*
import javax.inject.Singleton

object BigDecimalAdapter {
    @FromJson
    fun fromJson(string: String) = BigDecimal(string)

    @ToJson
    fun toJson(value: BigDecimal) = value.toString()
}

@Module
@InstallIn(SingletonComponent::class)
class MoshiModule {
    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi = Moshi.Builder()
        .add(Calendar::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .add(SerializeNulls.JSON_ADAPTER_FACTORY)
        .add(KotlinJsonAdapterFactory())
        .add(BigDecimalAdapter)
        .build()
}