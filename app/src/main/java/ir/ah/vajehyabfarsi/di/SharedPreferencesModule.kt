package ir.ah.vajehyabfarsi.di
import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Provides
    @Named(userSharedPreferences)
    fun provideUserSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences("SP_AUTH_TOKEN", MODE_PRIVATE)
}