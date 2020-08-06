package com.sunilsharma.mvvmsample.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sunilsharma.mvvmsample.data.repositories.UserRepository
import com.sunilsharma.mvvmsample.ui.auth.AuthViewModel

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory (
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}