package ir.ah.vajehyabfarsi.di

import dagger.*
import dagger.hilt.*
import dagger.hilt.components.*
import ir.ah.vajehyabfarsi.data.remot.*
import ir.ah.vajehyabfarsi.repository.*
import javax.inject.*

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    internal fun provideSearchRepository(api:ApiService ): SearchRepository =
        SearchRepositoryImpl(api)


}