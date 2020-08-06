package com.sunilsharma.mvvmsample.ui.auth

import com.sunilsharma.mvvmsample.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}