package com.example.myapplication3.famousQuote.repository

import androidx.lifecycle.LiveData
import com.example.myapplication3.architecture.CustomApplication
import com.example.myapplication3.architecture.RetrofitBuilder
import com.example.myapplication3.chuckNorris.model.QuotableRetrofit
import com.example.myapplication3.chuckNorris.model.QuotableRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotableRepository {
    private val mQuotableDao = CustomApplication.instance.mApplicationDatabase.QuotableDao()

    fun selectAllQuotableQuotes(): LiveData<List<QuotableRoom>> {
        return mQuotableDao.selectAll()
    }

    private suspend fun insertQuotableQuote(quotableRoom: QuotableRoom) =
        withContext(Dispatchers.IO) {
            mQuotableDao.insert(quotableRoom)
        }

    suspend fun deleteAllQuotableQuotes() =
        withContext(Dispatchers.IO) {
            mQuotableDao.deleteAll()
        }

    suspend fun fetchData() {
        insertQuotableQuote(RetrofitBuilder.getQuotable().getRandomQuote().toRoom())
    }

    private fun QuotableRetrofit.toRoom(): QuotableRoom {
        return QuotableRoom(
            content = content,
            author = author,
            dateAdded = dateAdded
        )
    }
}