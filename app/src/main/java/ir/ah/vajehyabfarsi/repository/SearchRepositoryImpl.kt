package ir.ah.vajehyabfarsi.repository

import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.data.remot.*
import ir.ah.vajehyabfarsi.other.util.*
import ir.ah.vajehyabfarsi.other.wrapper.*

class SearchRepositoryImpl(private val api: ApiService):SearchRepository {
    override suspend fun getWord(query: String, filter: String): Resource<VajehResponse> =
        safeApiCall { api.getWord(query,filter) }


    override suspend fun getSearchVajeh(query: String,filter:String): Resource<VajehResponse> =
        safeApiCall { api.getSearchVajeh(query) }
}