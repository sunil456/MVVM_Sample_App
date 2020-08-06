package com.sunilsharma.mvvmsample.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.sunilsharma.mvvmsample.data.repositories.UserRepository
import com.sunilsharma.mvvmsample.utils.ApiException
import com.sunilsharma.mvvmsample.utils.Coroutines
import com.sunilsharma.mvvmsample.utils.NoInternetException
import kotlinx.coroutines.coroutineScope

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View)
    {
        authListener?.onStarted()


        if (email.isNullOrEmpty() || password.isNullOrEmpty())
        {
            authListener?.onFailure("Invalid Email or Password")

            //Failure
            return
        }
        // Success
        Coroutines.main {

            try
            {
                val authResponse = repository.userLogin(email!!, password!!)

                authResponse.user?.let {user->
                    authListener?.onSuccess(user)
                    repository.saveUser(user)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            }
            catch (e: ApiException)
            {
                authListener?.onFailure(e.message!!)
            }
            catch (e: NoInternetException)
            {
                authListener?.onFailure(e.message!!)
            }
        }

    }

    fun onAllreadyHaveAccount(view: View)
    {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignup(view: View)
    {
        Intent(view.context, SingupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view: View)
    {
        authListener?.onStarted()

        if (name.isNullOrEmpty())
        {
            authListener?.onFailure("Name is required")
            return
        }
        if (email.isNullOrEmpty())
        {
            authListener?.onFailure("Email is required")
            return
        }

        if (password.isNullOrEmpty())
        {
            authListener?.onFailure("Password is required")
            return
        }
        if (passwordConfirm != passwordConfirm)
        {
            authListener?.onFailure("Password did not matched ")
            return
        }

        // Success
        Coroutines.main {

            try
            {
                val authResponse = repository.userSignup(name!!, email!!, password!!)

                authResponse.user?.let {user->
                    authListener?.onSuccess(user)
                    repository.saveUser(user)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            }
            catch (e: ApiException)
            {
                authListener?.onFailure(e.message!!)
            }
            catch (e: NoInternetException)
            {
                authListener?.onFailure(e.message!!)
            }
        }


    }

}