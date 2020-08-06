package com.sunilsharma.mvvmsample.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sunilsharma.mvvmsample.data.db.entities.Quote

@Dao
interface QuotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllQuotes(quote: List<Quote>)

    @Query("SELECT * FROM Quote")
    fun getQuotes(): LiveData<List<Quote>>
}