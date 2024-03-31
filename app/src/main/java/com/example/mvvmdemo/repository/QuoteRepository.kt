package com.example.mvvmdemo.repository


import androidx.lifecycle.LiveData
import com.example.mvvmdemo.roomdatabase.Quote
import com.example.mvvmdemo.roomdatabase.QuoteDao

class QuoteRepository(private val quoteDao: QuoteDao) {

    fun getQuotes() : LiveData<List<Quote>>{
        return quoteDao.getQuotes()
    }

    suspend fun insertQuote(quote: Quote){
        return quoteDao.insertQuote(quote)
    }
}