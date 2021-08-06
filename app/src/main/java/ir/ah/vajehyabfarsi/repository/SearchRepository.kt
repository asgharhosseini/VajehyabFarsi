package ir.ah.vajehyabfarsi.repository

import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.di.*
import ir.ah.vajehyabfarsi.other.wrapper.*
import retrofit2.Response
import retrofit2.http.*

interface SearchRepository {
    suspend fun  getWord(query:String,filter:String):Resource<VajehResponse>
    suspend  fun  getSearchVajeh(query:String):Resource<VajehResponse>
}