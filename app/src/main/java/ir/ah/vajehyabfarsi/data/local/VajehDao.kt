package ir.ah.vajehyabfarsi.data.local

import androidx.lifecycle.*
import androidx.room.*
import ir.ah.vajehyabfarsi.data.model.*
import ir.ah.vajehyabfarsi.data.model.response.*
import kotlinx.coroutines.flow.*

@Dao
interface VajehDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVajeh(vajeh: Vajeh)

    @Query("DELETE FROM vajeh WHERE id =:id")
    suspend fun deleteItem(id: String)

    @Query("SELECT * FROM vajeh")
    fun getAllFavorite(): Flow<List<Vajeh>>

    @Query("SELECT EXISTS (SELECT 1 FROM vajeh  WHERE id = :id)")
    fun checkVajehsFavorite(id: String): LiveData<Boolean>


}