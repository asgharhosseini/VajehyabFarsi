package ir.ah.vajehyabfarsi.repository.history

import androidx.room.*
import ir.ah.vajehyabfarsi.data.model.*
import kotlinx.coroutines.flow.*

interface HistoryRepository {
    suspend fun insertVajehHistory(vajeh: History)
    fun getAllHistory(): Flow<List<History>>


}