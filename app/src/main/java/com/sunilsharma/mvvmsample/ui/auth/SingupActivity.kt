package com.sunilsharma.mvvmsample.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sunilsharma.mvvmsample.R
import com.sunilsharma.mvvmsample.data.db.entities.User
import com.sunilsharma.mvvmsample.databinding.ActivityLoginBinding
import com.sunilsharma.mvvmsample.databinding.ActivitySingupBinding
import com.sunilsharma.mvvmsample.ui.home.HomeActivity
import com.sunilsharma.mvvmsample.utils.hide
import com.sunilsharma.mvvmsample.utils.show
import com.sunilsharma.mvvmsample.utils.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SingupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()

    private val factory : AuthViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_singup)

        val binding: ActivitySingupBinding = DataBindingUtil.setContentView(this, R.layout.activity_singup)
//        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user->
            if (user != null)
            {
                Intent(this, HomeActivity::class.java).also { intent ->
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackbar("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        root_layout.snackbar(message)
        progress_bar.hide()
    }
}