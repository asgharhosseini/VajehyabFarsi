package ir.ah.vajehyabfarsi.repository

import androidx.lifecycle.*
import androidx.room.*
import ir.ah.vajehyabfarsi.data.local.*
import ir.ah.vajehyabfarsi.data.model.*
import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.data.remot.*
import ir.ah.vajehyabfarsi.other.util.*
import ir.ah.vajehyabfarsi.other.wrapper.*
import kotlinx.coroutines.flow.*

class SearchRepositoryImpl(
    private val api: ApiService,
    private val database:VajehDao
) : SearchRepository {
    override suspend fun getWord(query: String, filter: String): Resource<VajehResponse> =
        safeApiCall { api.getWord(query, filter) }


    override suspend fun getSearchVajeh(query: String, filter: String): Resource<VajehResponse> =
        safeApiCall { api.getSearchVajeh(query) }


    override suspend fun insertVajeh(vajeh: Vajeh)=database.insertVajeh(vajeh)
    override suspend fun deleteItem(id: String)=database.deleteItem(id)
    override fun getAllFavorite(): Flow<List<Vajeh>> =database.getAllFavorite()
    override fun checkVajehsFavorite(id: String): LiveData<Boolean> =database.checkVajehsFavorite(id)



}