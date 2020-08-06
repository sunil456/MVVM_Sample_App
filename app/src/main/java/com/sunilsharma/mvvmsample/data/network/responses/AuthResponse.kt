package com.sunilsharma.mvvmsample.data.network.responses

import com.sunilsharma.mvvmsample.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)