package com.sunilsharma.mvvmsample.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sunilsharma.mvvmsample.data.repositories.QuotesRepository
import com.sunilsharma.mvvmsample.data.repositories.UserRepository
import com.sunilsharma.mvvmsample.ui.auth.AuthViewModel

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory (
    private val repository: QuotesRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}