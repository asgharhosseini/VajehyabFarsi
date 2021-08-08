package ir.ah.vajehyabfarsi.data.local

import androidx.room.*
import ir.ah.vajehyabfarsi.data.model.*
import ir.ah.vajehyabfarsi.data.model.response.*

@Database(entities = [Vajeh::class,History::class], version = 1)
abstract class VajehDatabase : RoomDatabase() {
    abstract fun vajehDao(): VajehDao
    abstract fun historyDao(): HistoryDao
}