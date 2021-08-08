package ir.ah.vajehyabfarsi.data.local

import androidx.lifecycle.*
import androidx.room.*
import ir.ah.vajehyabfarsi.data.model.*
import ir.ah.vajehyabfarsi.data.model.response.*
import kotlinx.coroutines.flow.*

@Dao
interface HistoryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVajehHistory(vajeh: History)

    @Query("SELECT * FROM History")
    fun getAllHistory(): LiveData<List<History>>

    @Query("DELETE  FROM History where id NOT IN (SELECT id from History ORDER BY id DESC LIMIT 10)")
    suspend fun deleteItemHistory()

}