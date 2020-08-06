package com.sunilsharma.mvvmsample.data.network.responses

import com.sunilsharma.mvvmsample.data.db.entities.Quote

data class QuotesResponse (
    val isSuccessful: Boolean,
    val quote: List<Quote>
)