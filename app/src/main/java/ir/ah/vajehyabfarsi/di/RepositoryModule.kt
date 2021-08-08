package ir.ah.vajehyabfarsi.di

import dagger.*
import dagger.hilt.*
import dagger.hilt.components.*
import ir.ah.vajehyabfarsi.data.local.*
import ir.ah.vajehyabfarsi.data.remot.*
import ir.ah.vajehyabfarsi.repository.history.*
import ir.ah.vajehyabfarsi.repository.search.*
import javax.inject.*

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    internal fun provideSearchRepository(api:ApiService,database: VajehDao): SearchRepository =
        SearchRepositoryImpl(api,database)
    @Provides
    @Singleton
    internal fun provideHistoryRepository(database: HistoryDao): HistoryRepository =
        HistoryRepositoryImpl(database)


}