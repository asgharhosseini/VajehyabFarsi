package ir.ah.vajehyabfarsi.data.remot

import ir.ah.vajehyabfarsi.data.model.response.*
import ir.ah.vajehyabfarsi.di.EndPoints.TOKEN
import retrofit2.Response
import retrofit2.http.*

interface ApiService {




    //,moein,amid,motaradef,farhangestan,sareh,ganjvajeh,wiki,slang,quran,name,thesis,isfahani,bakhtiari,tehrani,dezfuli,gonabadi,mazani,en2fa,ar2fa,fa2en,fa2ar
//    http://api.vajehyab.com/v3/search?token=72528.g8dvafpgHBgEqhnqZMErCgkirNzQ43slbGRlVgCC&q=&type=exact&filter=
    //search?token=72528.g8dvafpgHBgEqhnqZMErCgkirNzQ43slbGRlVgCC&
    //?token=
    @GET("search?token=?&q=?&type=?&filter=?")
   suspend fun  getWord(@Query("q")q:String,
                 @Query("filter")filter:String,
                 @Query("token")token:String=TOKEN,
                 @Query("type")type:String="exact"

    ):Response<VajehResponse>

    @GET("search?token=?&q=?&type=?&filter=?")
    suspend  fun  getSearchVajeh(@Query("q")q:String,
                @Query("token")token:String=TOKEN,
                @Query("type")type:String="exact",
                @Query("filter")filter:String="dehkhoda,moein,amid,motaradef,farhangestan,sareh,ganjvajeh,wiki,slang,quran,name,thesis,isfahani,bakhtiari,tehrani,dezfuli,gonabadi,mazani,en2fa,ar2fa,fa2en,fa2ar"
    ): Response<VajehResponse>



}