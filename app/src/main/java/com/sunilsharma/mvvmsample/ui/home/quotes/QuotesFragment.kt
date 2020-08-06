package com.sunilsharma.mvvmsample.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sunilsharma.mvvmsample.R
import com.sunilsharma.mvvmsample.ui.home.profile.ProfileViewModel
import com.sunilsharma.mvvmsample.ui.home.profile.ProfileViewModelFactory
import com.sunilsharma.mvvmsample.utils.Coroutines
import com.sunilsharma.mvvmsample.utils.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()


    private lateinit var viewModel: QuotesViewModel
    private val factory: QuotesViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)

        Coroutines.main {
            val quotes =  viewModel.quote.await()

            quotes.observe(viewLifecycleOwner, Observer {
                context?.toast(it.size.toString())
            })
        }

    }

}