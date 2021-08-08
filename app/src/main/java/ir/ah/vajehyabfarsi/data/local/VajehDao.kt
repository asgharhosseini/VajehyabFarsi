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

    @Query("DELETE FROM Vajeh WHERE id =:id")
    suspend fun deleteItem(id: String)

    @Query("SELECT * FROM  Vajeh")
    fun getAllFavorite(): Flow<List<Vajeh>>

    @Query("SELECT EXISTS (SELECT 1 FROM Vajeh  WHERE id = :id)")
    fun checkVajehsFavorite(id: String): LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVajehHistory(vajeh: History)

    @Query("SELECT * FROM History")
    fun getAllHistory(): Flow<List<History>>

    @Query("DELETE FROM history where id NOT IN (SELECT id from history ORDER BY id DESC LIMIT 10)")
    suspend fun deleteItemHistory(id: String)

}