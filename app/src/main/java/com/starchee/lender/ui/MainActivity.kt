package com.starchee.lender.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.starchee.lender.App
import com.starchee.lender.R
import com.starchee.lender.ui.utils.ViewModelProviderFactory
import com.starchee.lender.viewModels.MainActivityViewModel
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private var mainActivityViewModel: MainActivityViewModel? = null
    private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).mainActivityComponent.inject(this)
        mainActivityViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MainActivityViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        localePicker()
    }

    override fun attachBaseContext(newBase: Context) {
        newBase.resources.configuration.setLocale(Locale.getDefault())
        super.attachBaseContext(newBase)
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.loan_info_fragment -> navController.navigateUp()
            else -> super.onBackPressed()
        }

    }

    private fun localePicker() {
        mainActivityViewModel?.getLocale(Locale.getDefault().toLanguageTag())
        mainActivityViewModel?.locale?.observe(this, {
            if (it != Locale.getDefault().toLanguageTag()) {
                Locale.setDefault(Locale(it))
                recreate()
            }
        })
    }


}