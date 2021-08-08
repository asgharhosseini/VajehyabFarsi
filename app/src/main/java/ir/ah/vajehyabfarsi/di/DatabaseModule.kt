package ir.ah.vajehyabfarsi.di

import android.content.*
import androidx.room.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.android.qualifiers.*
import dagger.hilt.components.*
import ir.ah.vajehyabfarsi.data.local.*
import javax.inject.*

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): VajehDatabase {
        return Room.databaseBuilder(
            appContext,
            VajehDatabase::class.java,
            "order_db"
        ).allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideVajehDao(vajehDatabase: VajehDatabase): VajehDao {
        return vajehDatabase.orderDao()
    }
}