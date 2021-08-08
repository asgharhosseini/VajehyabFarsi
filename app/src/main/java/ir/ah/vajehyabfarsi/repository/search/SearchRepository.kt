package ir.ah.vajehyabfarsi.repository.search

import androidx.lifecycle.*
import ir.ah.vajehyabfarsi.data.model.*
import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.di.*
import ir.ah.vajehyabfarsi.other.wrapper.*
import kotlinx.coroutines.flow.*
import retrofit2.Response
import retrofit2.http.*

interface SearchRepository {
    suspend fun  getWord(query:String,filter:String):Resource<VajehResponse>
    suspend  fun  getSearchVajeh(query:String,filter:String):Resource<VajehResponse>

    suspend fun insertVajeh(vajeh: Vajeh)
    suspend fun deleteItem(id: String)
    fun getAllFavorite(): Flow<List<Vajeh>>
    fun checkVajehsFavorite(id: String): LiveData<Boolean>
}