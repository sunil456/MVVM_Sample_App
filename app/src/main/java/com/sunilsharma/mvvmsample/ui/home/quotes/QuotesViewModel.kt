package com.sunilsharma.mvvmsample.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.sunilsharma.mvvmsample.data.repositories.QuotesRepository
import com.sunilsharma.mvvmsample.utils.lazyDeferred

class QuotesViewModel(
    private val repository: QuotesRepository
) : ViewModel() {

    val quote by lazyDeferred { repository.getQuotes() }
}