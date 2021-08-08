package ir.ah.vajehyabfarsi.repository.history

import androidx.lifecycle.*
import androidx.room.*
import ir.ah.vajehyabfarsi.data.model.*
import kotlinx.coroutines.flow.*

interface HistoryRepository {
    suspend fun insertVajehHistory(vajeh: History)
    fun getAllHistory(): LiveData<List<History>>


}