package com.example.myapplication3.famousQuote.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.myapplication3.chuckNorris.model.QuotableRoom
import com.example.myapplication3.chuckNorris.model.QuotableUi
import com.example.myapplication3.famousQuote.repository.QuotableRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotableViewModel: ViewModel() {
    private val mQuoteRepository: QuotableRepository by lazy { QuotableRepository() }
    var mQuotableLiveData: LiveData<List<QuotableUi>> =
        mQuoteRepository.selectAllQuotableQuotes().map {
            it.toUi()
        }

    fun fetchNewQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            mQuoteRepository.fetchData()
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            mQuoteRepository.deleteAllQuotableQuotes()
        }
    }

    private fun List<QuotableRoom>.toUi(): List<QuotableUi> {
        return asSequence().map{
            QuotableUi(
                content = it.content,
                author = it.author,
                dateAdded = it.dateAdded
            )
        }.toList()
    }
}
