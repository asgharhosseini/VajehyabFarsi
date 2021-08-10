package ir.ah.vajehyabfarsi.repository.history

import androidx.lifecycle.*
import ir.ah.vajehyabfarsi.data.local.*
import ir.ah.vajehyabfarsi.data.model.*
import kotlinx.coroutines.flow.*
import javax.inject.*

class HistoryRepositoryImpl @Inject constructor(private val database:HistoryDao) :HistoryRepository {
    override suspend fun insertVajehHistory(vajeh: History) {
        database.insertVajehHistory(vajeh)
        database.deleteItemHistory()
    }

    override fun getAllHistory(): LiveData<List<History>> =database.getAllHistory()
    override suspend fun deleteItemHistory(id: Int) =database.deleteItemHistory(id)


}