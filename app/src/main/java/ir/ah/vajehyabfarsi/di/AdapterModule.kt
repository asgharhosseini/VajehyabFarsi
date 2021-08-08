package ir.ah.vajehyabfarsi.di

import dagger.*
import dagger.hilt.*
import dagger.hilt.android.components.*
import ir.ah.vajehyabfarsi.data.local.*
import ir.ah.vajehyabfarsi.ui.fragment.search.adapter.*

@Module
@InstallIn(FragmentComponent::class)
object AdapterModule {


    @Provides
    fun provideVajehAdapter(database: VajehDao) = VajehAdapter(database)



}