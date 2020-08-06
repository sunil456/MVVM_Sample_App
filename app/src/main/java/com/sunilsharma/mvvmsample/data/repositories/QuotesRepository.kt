package com.sunilsharma.mvvmsample.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunilsharma.mvvmsample.data.db.AppDatabase
import com.sunilsharma.mvvmsample.data.db.entities.Quote
import com.sunilsharma.mvvmsample.data.network.MyApi
import com.sunilsharma.mvvmsample.data.network.SafeApiRequest
import com.sunilsharma.mvvmsample.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepository (
    private val api : MyApi,
    private val db : AppDatabase
) : SafeApiRequest(){

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it!!)
        }
    }

    private suspend fun fetchQuotes()
    {
        if(isFetchNeeded())
        {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quote)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>>{

        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuotesDao().getQuotes()
        }
    }

    private fun isFetchNeeded(): Boolean {
        return true
    }


    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            db.getQuotesDao().saveAllQuotes(quotes)
        }
    }


}