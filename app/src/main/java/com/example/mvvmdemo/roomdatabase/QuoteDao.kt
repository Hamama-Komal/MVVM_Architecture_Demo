package com.example.mvvmdemo.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmdemo.roomdatabase.Quote

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote")
    fun getQuotes() : LiveData<List<Quote>>

    @Insert
    suspend fun insertQuote(quote: Quote)
}