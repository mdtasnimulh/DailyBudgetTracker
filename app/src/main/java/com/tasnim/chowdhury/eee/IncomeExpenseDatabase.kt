package com.tasnim.chowdhury.eee

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [IncomeExpense::class], version = 1, exportSchema = false)
abstract class IncomeExpenseDatabase : RoomDatabase() {

    abstract fun getIncomeExpenseDao(): IncomeExpenseDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: IncomeExpenseDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): IncomeExpenseDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IncomeExpenseDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}
