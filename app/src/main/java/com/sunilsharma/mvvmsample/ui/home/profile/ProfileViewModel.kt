package com.sunilsharma.mvvmsample.ui.home.profile

import androidx.lifecycle.ViewModel
import com.sunilsharma.mvvmsample.data.repositories.UserRepository

class ProfileViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()

}